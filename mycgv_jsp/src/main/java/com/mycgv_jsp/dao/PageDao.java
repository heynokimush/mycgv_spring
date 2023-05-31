package com.mycgv_jsp.dao;

import java.util.HashMap;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PageDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * 전체 카운트 가져오기 - 페이징 처리
	 */
	public int totalRowCount(String sname) {
		Map<String,String> param = new HashMap<String,String>();
		param.put("sname", sname);
		return sqlSession.selectOne("mapper.page.count", param);
//		int count = 0;
//		String sql = "select count(*) from mycgv_board";
//		getPreparedStatement(sql);
//		
//		try {
//			rs = pstmt.executeQuery();
//			while(rs.next()) {				
//				count = rs.getInt(1);
//			}			
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return count;		
	}	
}
