package com.mycgv_jsp.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv_jsp.service.MemberService;
import com.mycgv_jsp.service.NoticeService;
import com.mycgv_jsp.service.PageServiceImpl;
import com.mycgv_jsp.vo.NoticeVo;

@Controller
public class AdminController {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private PageServiceImpl pageService;
	
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
	public String admin_notice_write_proc(NoticeVo noticeVo) {
		String viewName = "";
		
		if(noticeService.getInsert(noticeVo) == 1) {
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
		
		if(noticeService.getUpdate(noticeVo) == 1) {
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
	 * admin_notice_delete_proc - 관리자 공지사항 삭제 처리
	 */
	@RequestMapping(value="/admin_notice_delete_proc.do",method=RequestMethod.POST)
	public String admin_notice_delete_proc(String nid) {
		String viewName = "";
		
		if(noticeService.getDelete(nid) == 1) {
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
