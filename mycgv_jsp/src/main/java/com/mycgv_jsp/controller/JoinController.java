package com.mycgv_jsp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv_jsp.dao.MemberDao;
import com.mycgv_jsp.service.MemberService;
import com.mycgv_jsp.service.MemberServiceImpl;
import com.mycgv_jsp.vo.MemberVo;

@Controller
public class JoinController {

	@Autowired
	private MemberService memberService;
	
	/**
	 * join.do - 회원가입 폼
	 */
	@RequestMapping(value="/join.do",method=RequestMethod.GET)
	public String join() {
		return "/join/join";
	}
	
	/**
	 * join_proc - 회원가입 처리
	 */
	@RequestMapping(value="/join_proc.do",method=RequestMethod.POST)
	public ModelAndView join_proc(MemberVo memberVo) {
		ModelAndView model = new ModelAndView();
		
		if(memberService.getJoinResult(memberVo)==1) { //insert 작업할때는 실패시 sql 쿼리 자체에서 익셉션이 떠 에러가 발생하므로 반환값이 0이 되는것이 아니다.
			model.addObject("join_result", "success");
			model.setViewName("/login/login");
		} else { //따라서 회원가입 실패 시 에러페이지를 호출해야함
		}
		
		return model;
	}
	
	/**
	 * id_check - 아이디 중복체크
	 */
	@RequestMapping(value="/id_check.do",method=RequestMethod.GET) //쿼리스트링방식이므로 -> GET
	@ResponseBody
	public String id_check(String id) {
		return memberService.getIdCheckResult(id);
	}
}
