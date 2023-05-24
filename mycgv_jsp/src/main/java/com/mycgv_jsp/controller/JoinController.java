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
	 * join.do - ȸ������ ��
	 */
	@RequestMapping(value="/join.do",method=RequestMethod.GET)
	public String join() {
		return "/join/join";
	}
	
	/**
	 * join_proc - ȸ������ ó��
	 */
	@RequestMapping(value="/join_proc.do",method=RequestMethod.POST)
	public ModelAndView join_proc(MemberVo memberVo) {
		ModelAndView model = new ModelAndView();
		
		if(memberService.getJoinResult(memberVo)==1) { //insert �۾��Ҷ��� ���н� sql ���� ��ü���� �ͼ����� �� ������ �߻��ϹǷ� ��ȯ���� 0�� �Ǵ°��� �ƴϴ�.
			model.addObject("join_result", "success");
			model.setViewName("/login/login");
		} else { //���� ȸ������ ���� �� ������������ ȣ���ؾ���
		}
		
		return model;
	}
	
	/**
	 * id_check - ���̵� �ߺ�üũ
	 */
	@RequestMapping(value="/id_check.do",method=RequestMethod.GET) //������Ʈ������̹Ƿ� -> GET
	@ResponseBody
	public String id_check(String id) {
		return memberService.getIdCheckResult(id);
	}
}
