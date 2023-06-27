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
	 * header �Խ���(json) ȣ��Ǵ� �ּ�
	 * @return
	 */
	@RequestMapping(value="/board_list_json.do",method=RequestMethod.GET)
	public String board_list_json() {
		return "/board/board_list_json";
	}
	
	/**
	 * board_list_json_data.do - ajax���� ȣ��Ǵ� �Խñ� ��ü ����Ʈ (json)
	 */
	@RequestMapping(value="/board_list_json_data.do",method=RequestMethod.GET,produces="text/plain;charset=UTF-8")
	@ResponseBody
	public String board_list_json_data(String page) {
		Map<String, Integer> param = pageService.getPageResult(page, "board");
		
		ArrayList<BoardVo> list = boardService.getSelect(param.get("startCount"), param.get("endCount"));
		
		//list ��ü�� �����͸� JSON ���·� ����
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
		
		return new Gson().toJson(jlist); //json�� ��������� string���� �Ѱ��ִ� ���
	}
	
	/**
	 * board_list.do - �Խñ� ��ü ����Ʈ -> ���������̼�
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
	 * board_content.do - �Խ��� ��
	 */
	@RequestMapping(value="/board_content.do",method=RequestMethod.GET)
	public ModelAndView board_content(String bid) {
		ModelAndView model = new ModelAndView();
		
		//DB���� �� ArrayList<BoardVo>
		BoardVo boardVo = boardService.getSelect(bid);
		if(boardVo != null) {
			boardService.getUpdateHits(bid);
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
	public String board_write_proc(BoardVo boardVo, HttpServletRequest request) throws Exception {
		String viewName = "";
		
//		//������ ������ġ
//		String root_path = request.getSession().getServletContext().getRealPath("/");
//		String attach_path = "\\resources\\upload\\";
		
		int result = boardService.getInsert(fileService.fileCheck(boardVo));
		if(result == 1){
			//viewName = "/board/board_list"; //jsp �������� ��ȯ�ϸ� ������ �����۾��� �ȵǱ� ������ �����Ͱ� ��µ��� ����
			
//			//������ �����ϸ� ������ ����
//			File saveFile = new File(root_path + attach_path + boardVo.getBsfile());
//			boardVo.getFile1().transferTo(saveFile);
			
			if(boardVo.getBfile() != null && !boardVo.getBfile().equals("")) {
				fileService.fileSave(boardVo, request);
			}
			
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
		
		model.addObject("boardVo",boardService.getSelect(bid));
		model.setViewName("/board/board_update");
		
		return model;
	}
	
	/**
	 * board_delete.do - �Խ��� �� �����ϱ�
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
	 * board_update_proc.do - �Խ��� �ۼ��� ó��
	 */
	@RequestMapping(value="/board_update_proc.do",method=RequestMethod.POST)
	public String board_update_proc(BoardVo boardVo, HttpServletRequest request) throws Exception {
		String viewName = "";
		
		String oldFileName = boardVo.getBsfile(); //���ο� ���� ������Ʈ �� ���� ���� ����(�Ű������� ���� ���ϸ��� �Ѿ��)
		
		int result = boardService.getUpdate((BoardVo)fileService.fileCheck(boardVo));
		if(result == 1) {
			if(boardVo.getBfile() != null && !boardVo.getBfile().equals("")) {
				fileService.fileSave(boardVo, request); //���ο� ���� ����
				fileService.fileDelete(boardVo, request, oldFileName);//���� ���� ����
			}
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
	public String board_delete_proc(String bid, String bsfile, HttpServletRequest request) throws Exception {
		String viewName = "";
		
		if(boardService.getDelete(bid) == 1) {
			fileService.fileDelete(request, bsfile);
			viewName = "redirect:/board_list.do";
		} else {
			//���������� ȣ��
		}
		
		return viewName;
	}
	
}
