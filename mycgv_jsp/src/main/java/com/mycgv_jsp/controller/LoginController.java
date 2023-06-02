package com.mycgv_jsp.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycgv_jsp.service.MemberService;
import com.mycgv_jsp.vo.MemberVo;

@Controller
public class LoginController {
	
	@Autowired
	private MemberService memberService;
	
	/**
	 * login.do - 로그인 폼
	 */
	@RequestMapping(value="/login.do",method=RequestMethod.GET)
	public String login() {
		return "/login/login";
	}
	
	/**
	 * login_fail.do - 로그인 실패
	 */
	@RequestMapping(value="/login_fail.do",method=RequestMethod.GET)
	public String login_fail() {
		return "/login/login_fail";
	}
	
	/**
	 * login_proc.do - 로그인 처리
	 */
	@RequestMapping(value="/login_proc.do",method=RequestMethod.POST)
	public ModelAndView login_proc(MemberVo memberVo, HttpSession session) {
		ModelAndView model = new ModelAndView();
		
		if(memberService.getLoginResult(memberVo) == 1) {
			session.setAttribute("sid",memberVo.getId());
			model.addObject("login_result", "success");
			model.setViewName("index");
		} else {
			model.setViewName("redirect:/login_fail.do");
		}
		
		return model;
	}
	
	/**
	 * logout.do - 로그아웃
	 */
	@RequestMapping(value="/logout.do",method=RequestMethod.GET)
	public ModelAndView logout(HttpSession session) {
		ModelAndView model = new ModelAndView();
		String sid = (String)session.getAttribute("sid");
		if(sid != null) {
			session.invalidate();
			model.addObject("logout_result","success");
		}
		
		model.setViewName("index");
		
		return model;
	}
}
