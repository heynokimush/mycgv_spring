package com.mycgv_jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MyPageController {
	/**
	 * mypage.do - 마이페이지
	 */
	@RequestMapping(value="/mypage.do",method=RequestMethod.GET)
	public String mypage() {
		return "/mypage/mypage";
	}
}
