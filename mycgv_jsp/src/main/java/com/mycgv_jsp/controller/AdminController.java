package com.mycgv_jsp.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv_jsp.dao.NoticeDao;
import com.mycgv_jsp.vo.NoticeVo;

@Controller
public class AdminController {
	/**
	 * admin - 관리자
	 */
	@RequestMapping(value="/admin_index.do",method=RequestMethod.GET)
	public String admin_index() {
		return "/admin/admin_index";
	}
	
	/**
	 * admin_notice_list - 관리자 공지사항 전체 리스트
	 */
	@RequestMapping(value="/admin_notice_list.do",method=RequestMethod.GET)
	public ModelAndView admin_notice_list() {
		ModelAndView model = new ModelAndView();
		
		NoticeDao noticeDao = new NoticeDao();
		ArrayList<NoticeVo> list = noticeDao.select();
		
		model.addObject("list", list);
		model.setViewName("/admin/notice/admin_notice_list");
		
		return model;
	}
	
	/**
	 * admin_notice_content - 관리자 공지사항 상세
	 */
	@RequestMapping(value="/admin_notice_content.do",method=RequestMethod.GET)
	public ModelAndView admin_notice_content(String nid) {
		ModelAndView model = new ModelAndView();
		
		NoticeDao noticeDao = new NoticeDao();
		NoticeVo noticeVo = noticeDao.select(nid);
//		if(noticeVo != null) {
//			noticeDao.updateHits(nid);
//		}
		
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
		
		NoticeDao noticeDao = new NoticeDao();
		int result = noticeDao.insert(noticeVo);
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
		
		NoticeDao noticeDao = new NoticeDao();
		NoticeVo noticeVo = noticeDao.select(nid);
		
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
		
		NoticeDao noticeDao = new NoticeDao();
		int result = noticeDao.update(noticeVo);
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
		
		NoticeDao noticeDao = new NoticeDao();
		int result = noticeDao.delete(nid);
		if(result == 1) {
			viewName = "redirect:/admin_notice_list.do";
		} else {
		}
		
		return viewName;
	}
}
