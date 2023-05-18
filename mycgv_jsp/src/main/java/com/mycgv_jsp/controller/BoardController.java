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
	 * board_list.do - �Խ��� ��ü ����Ʈ
	 */
	@RequestMapping(value="/board_list.do",method=RequestMethod.GET)
	public ModelAndView board_list() {
		ModelAndView model = new ModelAndView();
		
		//DB���� �� ArrayList<BoardVo>
		BoardDao boardDao = new BoardDao();
		ArrayList<BoardVo> list = boardDao.select();
		
		model.addObject("list", list);
		model.setViewName("/board/board_list");
		
		return model;
	}
	
	/**
	 * board_content.do - �Խ��� ��
	 */
	@RequestMapping(value="/board_content.do",method=RequestMethod.GET)
	public ModelAndView board_content(String bid) {
		ModelAndView model = new ModelAndView();
		
		//DB���� �� ArrayList<BoardVo>
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
	 * board_write.do - �Խ��� �۾���
	 */
	@RequestMapping(value="/board_write.do",method=RequestMethod.GET)
	public String board_write() {
		return "/board/board_write";
	}
	
	/**
	 * board_write_proc.do - �Խ��� �۾��� ó��
	 */
	@RequestMapping(value="/board_write_proc.do",method=RequestMethod.POST)
	public String board_write_proc(BoardVo boardVo) {
		String viewName = "";
		
		BoardDao boardDao = new BoardDao();
		int result = boardDao.insert(boardVo);
		if(result == 1){
			//viewName = "/board/board_list"; //jsp �������� ��ȯ�ϸ� ������ �����۾��� �ȵǱ� ������ �����Ͱ� ��µ��� ����
			viewName = "redirect:/board_list.do"; //���� db������ ������ controller�� ȣ�����־�� ��
		} else {
			//���������� ȣ��
		}
		
		return viewName;
	}
	
	/**
	 * board_update.do - �Խ��� �� �����ϱ�
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
	 * board_delete.do - �Խ��� �� �����ϱ�
	 */
	@RequestMapping(value="/board_delete.do",method=RequestMethod.GET)
	public ModelAndView board_delete(String bid) {
		ModelAndView model = new ModelAndView();
		model.addObject("bid", bid);
		model.setViewName("/board/board_delete");
		return model;
	}
	
	/**
	 * board_update_proc.do - �Խ��� �ۼ��� ó��
	 */
	@RequestMapping(value="/board_update_proc.do",method=RequestMethod.POST)
	public String board_update_proc(BoardVo boardVo) {
		String viewName = "";
		
		BoardDao boardDao = new BoardDao();
		int result = boardDao.update(boardVo);
		
		if(result == 1) {
			viewName = "redirect:/board_list.do";
		} else {
			//���������� ȣ��
		}
		
		return viewName;
	}
	
	/**
	 * board_delete_proc.do - �Խ��� �� ���� ó��
	 */
	@RequestMapping(value="/board_delete_proc.do",method=RequestMethod.POST)
	public String board_delete_proc(String bid) {
		String viewName = "";
		
		BoardDao boardDao = new BoardDao();
		int result = boardDao.delete(bid);
		
		if(result == 1) {
			viewName = "redirect:/board_list.do";
		} else {
			//���������� ȣ��
		}
		
		return viewName;
	}
	
}
