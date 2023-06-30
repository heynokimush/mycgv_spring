package com.mycgv_jsp.controller;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv_jsp.service.FileServiceImpl;
import com.mycgv_jsp.service.MemberService;
import com.mycgv_jsp.service.NoticeService;
import com.mycgv_jsp.service.PageServiceImpl;
import com.mycgv_jsp.vo.BoardVo;
import com.mycgv_jsp.vo.NoticeVo;

@Controller
public class AdminController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private PageServiceImpl pageService;
	
	@Autowired
	private FileServiceImpl fileService;
	
	/**
	 * admin - 관리자
	 */
	@RequestMapping(value="/admin_index.do",method=RequestMethod.GET)
	public String admin_index() {
		return "/admin/admin_index";
	}
	
	/**
	 * notice_list.do - 게시글 전체 리스트 -> 페이지네이션
	 * @return
	 */
	@RequestMapping(value="/admin_notice_list.do", method=RequestMethod.GET)
	public ModelAndView admin_notice_list(String page) {
		ModelAndView model = new ModelAndView();		
		
		Map<String, Integer> param = pageService.getPageResult(page, "notice");
		
		model.addObject("list", noticeService.getSelect(param.get("startCount"), param.get("endCount")));
		model.addObject("totals", param.get("totals"));
		model.addObject("pageSize", param.get("pageSize"));
		model.addObject("maxSize", param.get("maxSize"));
		model.addObject("page", param.get("page"));
		
		model.setViewName("/admin/notice/admin_notice_list");
		
		return model;
	} 
	
	/**
	 * admin_notice_content - 관리자 공지사항 상세
	 */
	@RequestMapping(value="/admin_notice_content.do",method=RequestMethod.GET)
	public ModelAndView admin_notice_content(String nid) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("noticeVo", noticeService.getSelect(nid));
		model.setViewName("/admin/notice/admin_notice_content");
		
		return model;
	}
	
	/**
	 * admin_notice_write - 관리자 공지사항 글쓰기
	 */
	@RequestMapping(value="/admin_notice_write.do",method=RequestMethod.GET)
	public String admin_notice_write() {
		return "/admin/notice/admin_notice_write";
	}
	
	/**
	 * admin_notice_write_proc - 관리자 공지사항 글쓰기 처리
	 */
	@RequestMapping(value="/admin_notice_write_proc.do",method=RequestMethod.POST)
	public String admin_notice_write_proc(NoticeVo noticeVo, HttpServletRequest request) throws Exception{
		String viewName = "";
		
		//멀티파일  체크 - fileService.multiFileCheck
		int result = noticeService.getInsert(fileService.multiFileCheck(noticeVo));
		if(result == 1) {
			if(!noticeVo.getFiles()[0].getOriginalFilename().equals("")) {
				fileService.multiFileSave(noticeVo, request);
			}
			
			viewName = "redirect:/admin_notice_list.do";
		} else {
			//에러페이지
		}
		
		return viewName;
	}
	
	/**
	 * admin_notice_update - 관리자 공지사항 수정하기
	 */
	@RequestMapping(value="/admin_notice_update.do",method=RequestMethod.GET)
	public ModelAndView admin_notice_update(String nid) {
		ModelAndView model = new ModelAndView();
		
		NoticeVo noticeVo = noticeService.getSelect(nid);
		
		model.addObject("noticeVo", noticeVo);
		model.setViewName("/admin/notice/admin_notice_update");
		
		return model;
	}
	
	/**
	 * admin_notice_update_proc - 관리자 공지사항 수정 처리
	 */
	@RequestMapping(value="/admin_notice_update_proc.do",method=RequestMethod.POST)
	public String admin_notice_update_proc(NoticeVo noticeVo, HttpServletRequest request) throws Exception {
		String viewName = "";
		ArrayList<String> oldFileNames = new ArrayList<String>();
		
		oldFileNames.add(noticeVo.getNsfile1());
		oldFileNames.add(noticeVo.getNsfile2());
		
		int result = noticeService.getUpdate(fileService.multiFileCheck(noticeVo));
		if(result == 1) {
			if(!noticeVo.getFiles()[0].getOriginalFilename().equals("") 
					|| !noticeVo.getFiles()[1].getOriginalFilename().equals("")) {
				fileService.multiFileSave(noticeVo, request); //새로운 파일 저장
				fileService.multiFileDelete(noticeVo, request, oldFileNames);//기존 파일 삭제
			}
			viewName = "redirect:/admin_notice_list.do";
		} else {
			//에러페이지 호출
		}
		
		return viewName;
	}
	
	/**
	 * admin_notice_delete - 관리자 공지사항 삭제하기
	 */
	@RequestMapping(value="/admin_notice_delete.do",method=RequestMethod.GET)
	public ModelAndView admin_notice_delete(String nid, String nsfile1, String nsfile2) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("nid", nid);
		model.addObject("nsfile1", nsfile1);
		model.addObject("nsfile2", nsfile2);
		model.setViewName("/admin/notice/admin_notice_delete");
		
		return model;
	}
	
	/**
	 * admin_notice_delete_proc - 관리자 공지사항 삭제 처리
	 */
	@RequestMapping(value="/admin_notice_delete_proc.do",method=RequestMethod.POST)
	public String admin_notice_delete_proc(String nid, String nsfile1, String nsfile2, HttpServletRequest request) 
			throws Exception {
		String viewName = "";
		ArrayList<String> oldFileNames = new ArrayList<String>();
		oldFileNames.add(nsfile1);
		oldFileNames.add(nsfile2);
		
		if(noticeService.getDelete(nid) == 1) {
			fileService.multiFileDelete(request, oldFileNames);
			viewName = "redirect:/admin_notice_list.do";
		} else {
		}
		
		return viewName;
	}
	
	/**
	 * admin_member_list - 관리자 멤버 리스트
	 */
	@RequestMapping(value="/admin_member_list.do",method=RequestMethod.GET)
	public ModelAndView admin_member_list(String page) {
		ModelAndView model = new ModelAndView();
		
		Map<String,Integer> param = pageService.getPageResult(page, "member");
		
		model.addObject("list", memberService.getList(param.get("startCount"), param.get("endCount")));
		model.addObject("totals", param.get("totals"));
		model.addObject("pageSize", param.get("pageSize"));
		model.addObject("maxSize", param.get("maxSize"));
		model.addObject("page", param.get("page"));
		
		model.setViewName("/admin/member/admin_member_list");
		
		return model;
	}
}
