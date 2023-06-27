package com.mycgv_jsp.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mycgv_jsp.service.BoardService;
import com.mycgv_jsp.service.FileServiceImpl;
import com.mycgv_jsp.service.PageServiceImpl;
import com.mycgv_jsp.vo.BoardVo;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@Autowired
	private PageServiceImpl pageService;
	
	@Autowired
	private FileServiceImpl fileService;
	
	/**
	 * header 게시판(json) 호출되는 주소
	 * @return
	 */
	@RequestMapping(value="/board_list_json.do",method=RequestMethod.GET)
	public String board_list_json() {
		return "/board/board_list_json";
	}
	
	/**
	 * board_list_json_data.do - ajax에서 호출되는 게시글 전체 리스트 (json)
	 */
	@RequestMapping(value="/board_list_json_data.do",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String board_list_json_data(String page) {
		Map<String, Integer> param = pageService.getPageResult(page, "board");
		
		ArrayList<BoardVo> list = boardService.getSelect(param.get("startCount"), param.get("endCount"));
		
		//list 객체의 데이터를 JSON 형태로 생성
		JsonObject jlist = new JsonObject();
		JsonArray jarray = new JsonArray();
		
		for(BoardVo boardVo : list) {
			JsonObject jobj = new JsonObject(); //{}
			jobj.addProperty("rno", boardVo.getRno()); //{rno:1}
			jobj.addProperty("bid", boardVo.getBid()); //{rno:1}
			jobj.addProperty("btitle", boardVo.getBtitle()); //{rno:1,btitle:"~"}
			jobj.addProperty("bhits", boardVo.getBhits());
			jobj.addProperty("id", boardVo.getId());
			jobj.addProperty("bdate", boardVo.getBdate());
			
			jarray.add(jobj);
		}
		
		jlist.add("jlist", jarray);
		jlist.addProperty("totals", param.get("totals"));
		jlist.addProperty("pageSize", param.get("pageSize"));
		jlist.addProperty("maxSize", param.get("maxSize"));
		jlist.addProperty("page", param.get("page"));
		
		return new Gson().toJson(jlist); //json의 모습이지만 string으로 넘겨주는 방식
	}
	
	/**
	 * board_list.do - 게시글 전체 리스트 -> 페이지네이션
	 * @return
	 */
	@RequestMapping(value="/board_list.do", method=RequestMethod.GET)
	public ModelAndView board_list(String page) {
		ModelAndView model = new ModelAndView();	
		
		Map<String,Integer> param = pageService.getPageResult(page, "board");
		
		model.addObject("list", boardService.getSelect(param.get("startCount"), param.get("endCount")));
		model.addObject("totals", param.get("totals"));
		model.addObject("pageSize", param.get("pageSize"));
		model.addObject("maxSize", param.get("maxSize"));
		model.addObject("page", param.get("page"));
		
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
		BoardVo boardVo = boardService.getSelect(bid);
		if(boardVo != null) {
			boardService.getUpdateHits(bid);
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
	public String board_write_proc(BoardVo boardVo, HttpServletRequest request) throws Exception {
		String viewName = "";
		
//		//파일의 저장위치
//		String root_path = request.getSession().getServletContext().getRealPath("/");
//		String attach_path = "\\resources\\upload\\";
		
		int result = boardService.getInsert(fileService.fileCheck(boardVo));
		if(result == 1){
			//viewName = "/board/board_list"; //jsp 페이지만 반환하면 데이터 연동작업이 안되기 때문에 데이터가 출력되지 않음
			
//			//파일이 존재하면 서버에 저장
//			File saveFile = new File(root_path + attach_path + boardVo.getBsfile());
//			boardVo.getFile1().transferTo(saveFile);
			
			if(boardVo.getBfile() != null && !boardVo.getBfile().equals("")) {
				fileService.fileSave(boardVo, request);
			}
			
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
		
		model.addObject("boardVo",boardService.getSelect(bid));
		model.setViewName("/board/board_update");
		
		return model;
	}
	
	/**
	 * board_delete.do - 게시판 글 삭제하기
	 */
	@RequestMapping(value="/board_delete.do",method=RequestMethod.GET)
	public ModelAndView board_delete(String bid, String bsfile) {
		ModelAndView model = new ModelAndView();
		
		model.addObject("bid", bid);
		model.addObject("bsfile", bsfile);
		model.setViewName("/board/board_delete");
		
		return model;
	}
	
	/**
	 * board_update_proc.do - 게시판 글수정 처리
	 */
	@RequestMapping(value="/board_update_proc.do",method=RequestMethod.POST)
	public String board_update_proc(BoardVo boardVo, HttpServletRequest request) throws Exception {
		String viewName = "";
		
		String oldFileName = boardVo.getBsfile(); //새로운 파일 업데이트 시 기존 파일 삭제(매개변수로 기존 파일명이 넘어옴)
		
		int result = boardService.getUpdate((BoardVo)fileService.fileCheck(boardVo));
		if(result == 1) {
			if(boardVo.getBfile() != null && !boardVo.getBfile().equals("")) {
				fileService.fileSave(boardVo, request); //새로운 파일 저장
				fileService.fileDelete(boardVo, request, oldFileName);//기존 파일 삭제
			}
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
	public String board_delete_proc(String bid, String bsfile, HttpServletRequest request) throws Exception {
		String viewName = "";
		
		if(boardService.getDelete(bid) == 1) {
			fileService.fileDelete(request, bsfile);
			viewName = "redirect:/board_list.do";
		} else {
			//에러페이지 호출
		}
		
		return viewName;
	}
	
}
