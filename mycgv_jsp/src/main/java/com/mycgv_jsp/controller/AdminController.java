package com.mycgv_jsp.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv_jsp.dao.NoticeDao;
import com.mycgv_jsp.service.MemberService;
import com.mycgv_jsp.service.NoticeService;
import com.mycgv_jsp.vo.NoticeVo;

@Controller
public class AdminController {

	@Autowired
	private MemberService memberService;
	private NoticeService noticeService;
	
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
		
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 10;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = noticeService.getCount();	//DB에서 가져온 전체 행수
		
		//총 페이지 수 계산
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}

		//요청 페이지 계산
		if(page != null){
			reqPage = Integer.parseInt(page);
			startCount = (reqPage-1) * pageSize+1; 
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}
		
		ArrayList<NoticeVo> list = noticeService.getSelect(startCount, endCount);
	
		model.addObject("list", list);
		model.addObject("totals", dbCount);
		model.addObject("pageSize", pageSize);
		model.addObject("maxSize", pageCount);
		model.addObject("page", reqPage);
		
		model.setViewName("/admin/notice/admin_notice_list");
		
		return model;
	} 
	
	/**
	 * admin_notice_content - 관리자 공지사항 상세
	 */
	@RequestMapping(value="/admin_notice_content.do",method=RequestMethod.GET)
	public ModelAndView admin_notice_content(String nid) {
		ModelAndView model = new ModelAndView();
		
		NoticeVo noticeVo = noticeService.getSelect(nid);
		
		model.addObject("noticeVo", noticeVo);
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
	public String admin_notice_write_proc(NoticeVo noticeVo) {
		String viewName = "";
		
		int result = noticeService.getInsert(noticeVo);
		if(result == 1) {
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
	public String admin_notice_update_proc(NoticeVo noticeVo) {
		String viewName = "";
		
		int result = noticeService.getUpdate(noticeVo);
		if(result == 1) {
			viewName = "redirect:/admin_notice_list.do";
		} else {
		}
		
		return viewName;
	}
	
	/**
	 * admin_notice_delete - 관리자 공지사항 삭제하기
	 */
	@RequestMapping(value="/admin_notice_delete.do",method=RequestMethod.GET)
	public ModelAndView admin_notice_delete(String nid) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("nid", nid);
		model.setViewName("/admin/notice/admin_notice_delete");
		
		return model;
	}
	
	/**
	 * admin_notice_delete_proc - 관리자 공지사항 수정 처리
	 */
	@RequestMapping(value="/admin_notice_delete_proc.do",method=RequestMethod.POST)
	public String admin_notice_delete_proc(String nid) {
		String viewName = "";
		
		int result = noticeService.getDelete(nid);
		if(result == 1) {
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
		
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = memberService.getCount();	//DB에서 가져온 전체 행수
		
		//총 페이지 수 계산
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}

		//요청 페이지 계산
		if(page != null){
			reqPage = Integer.parseInt(page);
			startCount = (reqPage-1) * pageSize+1; 
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}
		
		model.addObject("list", memberService.getList(startCount, endCount));
		model.addObject("totals", dbCount);
		model.addObject("pageSize", pageSize);
		model.addObject("maxSize", pageCount);
		model.addObject("page", reqPage);
		
		model.setViewName("/admin/member/admin_member_list");
		
		return model;
	}
}
