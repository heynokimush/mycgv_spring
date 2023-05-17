package com.mycgv_jsp.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv_jsp.dao.BoardDao;
import com.mycgv_jsp.vo.BoardVo;

@Controller
public class BoardController {
	/**
	 * board_list.do - 게시판 전체 리스트
	 */
	@RequestMapping(value="/board_list.do",method=RequestMethod.GET)
	public ModelAndView board_list() {
		ModelAndView model = new ModelAndView();
		
		//DB연동 후 ArrayList<BoardVo>
		BoardDao boardDao = new BoardDao();
		ArrayList<BoardVo> list = boardDao.select();
		
		model.addObject("list", list);
		model.setViewName("/board/board_list");
		
		return model;
	}
	
	/**
	 * board_content.do - 게시판 상세
	 */
	@RequestMapping(value="/board_content.do",method=RequestMethod.GET)
	public ModelAndView board_content(String bid) {
		ModelAndView model = new ModelAndView();
		
		//DB연동 후 ArrayList<BoardVo>
		BoardDao boardDao = new BoardDao();
		BoardVo boardVo = boardDao.select(bid);
		
		model.addObject("bvo", boardVo);
		model.setViewName("/board/board_content");
		
		return model;
	}
}
