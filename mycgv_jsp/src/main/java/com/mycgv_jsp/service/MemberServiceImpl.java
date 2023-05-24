package com.mycgv_jsp.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mycgv_jsp.dao.MemberDao;
import com.mycgv_jsp.vo.MemberVo;

@Service
public class MemberServiceImpl implements MemberService{
	MemberDao memberDao = new MemberDao();
	
	@Override
	public int getLoginResult(MemberVo memberVo) {
		return memberDao.loginCheck(memberVo);
	}
	
	@Override
	public String getIdCheckResult(String id) {
		return String.valueOf(memberDao.idCheck(id));
	}
	
	@Override
	public int getJoinResult(MemberVo memberVo) {
		return memberDao.insert(memberVo);
	}
	
	@Override
	public ArrayList<MemberVo> getList(int startCount, int endCount) {
		return memberDao.select(startCount, endCount);
	}
	
	public int getCount() {
		return memberDao.totalRowCount();
	}
}
