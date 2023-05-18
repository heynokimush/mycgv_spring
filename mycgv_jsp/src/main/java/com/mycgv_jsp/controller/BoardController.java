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
		if(boardVo != null) {
			boardDao.updateHits(bid);
		}
		
		model.addObject("bvo", boardVo);
		model.setViewName("/board/board_content");
		
		return model;
	}
	
	/**
	 * board_write.do - 게시판 글쓰기
	 */
	@RequestMapping(value="/board_write.do",method=RequestMethod.GET)
	public String board_write() {
		return "/board/board_write";
	}
	
	/**
	 * board_write_proc.do - 게시판 글쓰기 처리
	 */
	@RequestMapping(value="/board_write_proc.do",method=RequestMethod.POST)
	public String board_write_proc(BoardVo boardVo) {
		String viewName = "";
		
		BoardDao boardDao = new BoardDao();
		int result = boardDao.insert(boardVo);
		if(result == 1){
			//viewName = "/board/board_list"; //jsp 페이지만 반환하면 데이터 연동작업이 안되기 때문에 데이터가 출력되지 않음
			viewName = "redirect:/board_list.do"; //따라서 db연동이 가능한 controller를 호출해주어야 함
		} else {
			//에러페이지 호출
		}
		
		return viewName;
	}
	
	/**
	 * board_update.do - 게시판 글 수정하기
	 */
	@RequestMapping(value="/board_update.do",method=RequestMethod.GET)
	public ModelAndView board_update(String bid) {
		ModelAndView model = new ModelAndView();
		BoardDao boardDao = new BoardDao();
		BoardVo boardVo = boardDao.select(bid);
		
		model.addObject("boardVo",boardVo);
		model.setViewName("/board/board_update");
		
		return model;
	}
	
	/**
	 * board_delete.do - 게시판 글 삭제하기
	 */
	@RequestMapping(value="/board_delete.do",method=RequestMethod.GET)
	public ModelAndView board_delete(String bid) {
		ModelAndView model = new ModelAndView();
		model.addObject("bid", bid);
		model.setViewName("/board/board_delete");
		return model;
	}
	
	/**
	 * board_update_proc.do - 게시판 글수정 처리
	 */
	@RequestMapping(value="/board_update_proc.do",method=RequestMethod.POST)
	public String board_update_proc(BoardVo boardVo) {
		String viewName = "";
		
		BoardDao boardDao = new BoardDao();
		int result = boardDao.update(boardVo);
		
		if(result == 1) {
			viewName = "redirect:/board_list.do";
		} else {
			//에러페이지 호출
		}
		
		return viewName;
	}
	
	/**
	 * board_delete_proc.do - 게시판 글 삭제 처리
	 */
	@RequestMapping(value="/board_delete_proc.do",method=RequestMethod.POST)
	public String board_delete_proc(String bid) {
		String viewName = "";
		
		BoardDao boardDao = new BoardDao();
		int result = boardDao.delete(bid);
		
		if(result == 1) {
			viewName = "redirect:/board_list.do";
		} else {
			//에러페이지 호출
		}
		
		return viewName;
	}
	
}
