package com.mycgv_jsp.controller;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv_jsp.service.NoticeService;
import com.mycgv_jsp.service.PageServiceImpl;
import com.mycgv_jsp.vo.NoticeVo;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;
	
	@Autowired
	private PageServiceImpl pageService;
	
	/**
	 * notice_list.do - 게시글 전체 리스트 -> 페이지네이션
	 * @return
	 */
	@RequestMapping(value="/notice_list.do", method=RequestMethod.GET)
	public ModelAndView notice_list(String page) {
		ModelAndView model = new ModelAndView();
		Map<String, Integer> param = pageService.getPageResult(page, "notice");
	
		model.addObject("list", noticeService.getSelect(param.get("startCount"), param.get("endCount")));
		model.addObject("totals", param.get("totals"));
		model.addObject("pageSize", param.get("pageSize"));
		model.addObject("maxSize", param.get("maxSize"));
		model.addObject("page", param.get("page"));
		
		model.setViewName("/notice/notice_list");
		
		return model;
	} 
	
	/**
	 * notice_content - 공지사항 상세
	 */
	@RequestMapping(value="/notice_content.do",method=RequestMethod.GET)
	public ModelAndView notice_content(String nid) {
		ModelAndView model = new ModelAndView();
		
		NoticeVo noticeVo = noticeService.getSelect(nid);
		if(noticeVo != null) {
			noticeService.getUpdateHits(nid);
		}
		
		model.addObject("noticeVo", noticeVo);
		model.setViewName("/notice/notice_content");
		
		return model;
	}
}
