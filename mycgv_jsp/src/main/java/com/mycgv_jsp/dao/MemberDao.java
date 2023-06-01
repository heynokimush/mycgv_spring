package com.mycgv_jsp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv_jsp.vo.MemberVo;

@Repository
public class MemberDao extends DBConn{ //Repository를 붙이면 비즈니스 로직을 수행하지 않고, 마이바티스에게 넘기게 된다.

	@Autowired
	private SqlSessionTemplate sqlSession;	//DBConn 대신 db연동 역할
	
	/**
	 * select - 회원관리
	 */
	public ArrayList<MemberVo> select() {
		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
		
		String sql = "select rownum rno, id, name, to_char(mdate,'yyyy-mm-dd') mdate, grade\r\n" + 
				"from (select id, name, mdate, grade from MYCGV_MEMBER order by mdate)\r\n" + 
				"order by rno";
		getPreparedStatement(sql);
		
		try {
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberVo memberVo = new MemberVo();
				
				memberVo.setRno(rs.getInt(1));
				memberVo.setId(rs.getString(2));
				memberVo.setName(rs.getString(3));
				memberVo.setMdate(rs.getString(4));
				memberVo.setGrade(rs.getString(5));
				
				list.add(memberVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * select - 멤버 전체 리스트 -> 페이징
	 */
	public ArrayList<MemberVo> select(int startCount, int endCount) {
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<MemberVo> list = sqlSession.selectList("mapper.member.list",param); //sqlSession.selectList("sql위치",파라미터);
		
		return (ArrayList<MemberVo>)list;
		
//		ArrayList<MemberVo> list = new ArrayList<MemberVo>();
//		
//		String sql = "select rno, id, name, mdate, grade\r\n" + 
//				"from (select rownum rno, id, name, to_char(mdate,'yyyy-mm-dd') mdate, grade\r\n" + 
//				"from (select id, name, mdate, grade from mycgv_member\r\n" + 
//				"          order by mdate desc))\r\n" + 
//				"where rno between ? and ?";
//		getPreparedStatement(sql);
//		
//		try {
//			pstmt.setInt(1, startCount);
//			pstmt.setInt(2, endCount);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				MemberVo memberVo = new MemberVo();
//				
//				memberVo.setRno(rs.getInt(1));
//				memberVo.setId(rs.getString(2));
//				memberVo.setName(rs.getString(3));
//				memberVo.setMdate(rs.getString(4));
//				memberVo.setGrade(rs.getString(5));
//				
//				list.add(memberVo);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return list;
	}
	
	/**
	 * insert - 회원가입
	 */
	public int insert(MemberVo memberVo) {
		return sqlSession.insert("mapper.member.join", memberVo);
//		int result = 0;
//		String sql = "insert into mycgv_member(id,pass,name,gender,email,addr,tel,pnumber,hobbylist,intro,mdate,grade)"
//				+ " values(?,?,?,?,?,?,?,?,?,?,sysdate,'GOLD')";
//		getPreparedStatement(sql);
//		
//		try {
//			pstmt.setString(1, memberVo.getId());
//			pstmt.setString(2, memberVo.getPass());
//			pstmt.setString(3, memberVo.getName());
//			pstmt.setString(4, memberVo.getGender());
//			pstmt.setString(5, memberVo.getEmail());
//			pstmt.setString(6, memberVo.getAddr());
//			pstmt.setString(7, memberVo.getTel());
//			pstmt.setString(8, memberVo.getPnumber());
//			pstmt.setString(9, memberVo.getHobbyList());
//			pstmt.setString(10, memberVo.getIntro());
//			
//			result = pstmt.executeUpdate();
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
	}
	
	/**
	 * idCheck - 아이디 중복체크
	 */
	public int idCheck(String id) {
		return sqlSession.selectOne("mapper.member.idcheck",id);
//		int result = 0;
//		
//		String sql = "select count(*) from mycgv_member where id=?";
//		getPreparedStatement(sql);
//		
//		try {
//			pstmt.setString(1, id);
//			
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				result = rs.getInt(1);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
	}
	
	/**
	 * loginCheck - 로그인 체크
	 */
	public int loginCheck(MemberVo memberVo) {
		return sqlSession.selectOne("mapper.member.login", memberVo);
//		int result = 0;
//		
//		String sql = "select count(*) from mycgv_member where id=? and pass=?";
//		getPreparedStatement(sql);
//		
//		try {
//			pstmt.setString(1, memberVo.getId());
//			pstmt.setString(2, memberVo.getPass());
//			
//			rs = pstmt.executeQuery();
//			while(rs.next()) {
//				result = rs.getInt(1);
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
	}
	
	/**
	 * 전체 카운트 가져오기
	 */
	public int totalRowCount() {
		int count = 0;
		String sql = "select count(*) from mycgv_member";
		getPreparedStatement(sql);
		
		try {
			rs = pstmt.executeQuery();
			while(rs.next()) {				
				count = rs.getInt(1);
			}			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;		
	}	
}
