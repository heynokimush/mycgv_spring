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
public class MemberDao implements MycgvDao{ //Repository�� ���̸� ����Ͻ� ������ �������� �ʰ�, ���̹�Ƽ������ �ѱ�� �ȴ�.

	@Autowired
	private SqlSessionTemplate sqlSession;	//DBConn ��� db���� ����
	
	/**
	 * select - ȸ������
	 */
	public ArrayList<MemberVo> select() {
		List<MemberVo> list = sqlSession.selectList("mapper.member.list2"); //sqlSession.selectList("sql��ġ",�Ķ����);
		
		return (ArrayList<MemberVo>)list;
	}
	
	/**
	 * select - ��� ��ü ����Ʈ -> ����¡
	 */
	@Override
	public List<Object> select(int startCount, int endCount) {
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		return sqlSession.selectList("mapper.member.list",param);
		
	}
	
	/**
	 * insert - ȸ������
	 */
	@Override
	public int insert(Object memberVo) {
		return sqlSession.insert("mapper.member.insert",memberVo);
	}
//	public int insert(MemberVo memberVo) {
//		return sqlSession.insert("mapper.member.join", memberVo);
//	}
	
	/**
	 * idCheck - ���̵� �ߺ�üũ
	 */
	public int idCheck(String id) {
		return sqlSession.selectOne("mapper.member.idcheck",id);
	}
	
	/**
	 * loginCheck - �α��� üũ
	 */
	public SessionVo loginCheck(MemberVo memberVo) {
		return sqlSession.selectOne("mapper.member.login", memberVo);
	}
	
}
