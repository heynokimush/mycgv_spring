package com.mycgv_jsp.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycgv_jsp.dao.BoardDao;
import com.mycgv_jsp.vo.BoardVo;

@Controller
public class BoardController {
	/**
	 * board_list_json.do - 게시판 전체 리스트 (json)
	 */
	@RequestMapping(value="/board_list_json.do",method=RequestMethod.GET)
	@ResponseBody
	public String board_list_json(String page) {
		BoardDao boardDao = new BoardDao();
		
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = boardDao.totalRowCount();	//DB에서 가져온 전체 행수
		
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
		
		ArrayList<BoardVo> list = boardDao.select(startCount, endCount);
		
		//list 객체의 데이터를 JSON 형태로 생성
		JsonObject jlist = new JsonObject();
		JsonArray jarray = new JsonArray();
		
		for(BoardVo boardVo : list) {
			JsonObject jobj = new JsonObject(); //{}
			jobj.addProperty("rno", boardVo.getRno()); //{rno:1}
			jobj.addProperty("btitle", boardVo.getBtitle()); //{rno:1,btitle:"~"}
			jobj.addProperty("bhits", boardVo.getBhits());
			jobj.addProperty("id", boardVo.getId());
			jobj.addProperty("bdate", boardVo.getBdate());
			
			jarray.add(jobj);
		}
		
		jlist.add("jlist", jarray);
		
//		model.addObject("list", list);
//		model.addObject("totals", dbCount);
//		model.addObject("pageSize", pageSize);
//		model.addObject("maxSize", pageCount);
//		model.addObject("page", reqPage);
//		
//		model.setViewName("/board/board_list");
		
		return new Gson().toJson(jlist); //json의 모습이지만 string으로 넘겨주는 방식
	}
	
	/**
	 * board_list.do - 게시글 전체 리스트 -> 페이지네이션
	 * @return
	 */
	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
	public ModelAndView board_list(String page) {
		ModelAndView model = new ModelAndView();		
		BoardDao boardDao = new BoardDao();
		
		//페이징 처리 - startCount, endCount 구하기
		int startCount = 0;
		int endCount = 0;
		int pageSize = 5;	//한페이지당 게시물 수
		int reqPage = 1;	//요청페이지	
		int pageCount = 1;	//전체 페이지 수
		int dbCount = boardDao.totalRowCount();	//DB에서 가져온 전체 행수
		
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
		
		ArrayList<BoardVo> list = boardDao.select(startCount, endCount);
	
		model.addObject("list", list);
		model.addObject("totals", dbCount);
		model.addObject("pageSize", pageSize);
		model.addObject("maxSize", pageCount);
		model.addObject("page", reqPage);
		
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
