package com.mycgv_jsp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminController {
	/**
	 * Admin - °ü¸®ÀÚ
	 */
	@RequestMapping(value="/admin_index.do",method=RequestMethod.GET)
	public String admin() {
		return "admin/admin_index";
	}
}
