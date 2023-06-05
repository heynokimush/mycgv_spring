package com.mycgv_jsp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv_jsp.vo.MemberVo;
import com.mycgv_jsp.vo.SessionVo;

@Repository
public class MemberDao implements MycgvDao{ //Repository를 붙이면 비즈니스 로직을 수행하지 않고, 마이바티스에게 넘기게 된다.

	@Autowired
	private SqlSessionTemplate sqlSession;	//DBConn 대신 db연동 역할
	
	/**
	 * select - 회원관리
	 */
	public ArrayList<MemberVo> select() {
		List<MemberVo> list = sqlSession.selectList("mapper.member.list2"); //sqlSession.selectList("sql위치",파라미터);
		
		return (ArrayList<MemberVo>)list;
	}
	
	/**
	 * select - 멤버 전체 리스트 -> 페이징
	 */
	@Override
	public List<Object> select(int startCount, int endCount) {
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		return sqlSession.selectList("mapper.member.list",param);
		
	}
	
	/**
	 * insert - 회원가입
	 */
	@Override
	public int insert(Object memberVo) {
		return sqlSession.insert("mapper.member.insert",memberVo);
	}
//	public int insert(MemberVo memberVo) {
//		return sqlSession.insert("mapper.member.join", memberVo);
//	}
	
	/**
	 * idCheck - 아이디 중복체크
	 */
	public int idCheck(String id) {
		return sqlSession.selectOne("mapper.member.idcheck",id);
	}
	
	/**
	 * loginCheck - 로그인 체크
	 */
	public SessionVo loginCheck(MemberVo memberVo) {
		return sqlSession.selectOne("mapper.member.login", memberVo);
	}
	
}
