package com.mycgv_jsp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv_jsp.vo.NoticeVo;

@Repository
public class NoticeDao extends DBConn {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * select - 공지사항 전체 리스트
	 */
	public ArrayList<NoticeVo> select(){
		List<NoticeVo> list = sqlSession.selectList("mapper.notice.list2");
		
		return (ArrayList<NoticeVo>)list;
	}
	
	/**
	 * select - 게시글 전체 리스트 -> 페이징
	 */
	public ArrayList<NoticeVo> select(int startCount, int endCount) {
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<NoticeVo> list = sqlSession.selectList("mapper.notice.list",param);
		return (ArrayList<NoticeVo>)list;
	}
	
	/**
	 * select - 공지사항 상세
	 */
	public NoticeVo select(String nid){
		return sqlSession.selectOne("mapper.notice.content",nid);
	}
	
	/**
	 * insert - 공지사항 글쓰기
	 */
	public int insert(NoticeVo noticeVo){
		return sqlSession.insert("mapper.notice.insert",noticeVo);
	}
	
	/**
	 * update - 공지사항 수정
	 */
	public int update(NoticeVo noticeVo){
		return sqlSession.update("mapper.notice.update",noticeVo);
	}
	
	/**
	 * update - 공지사항 수정
	 */
	public int delete(String nid){
		return sqlSession.delete("mapper.notice.delete",nid);
	}
	
	/**
	 * updateHits - 공지사항 조회수 수정
	 */
	public void updateHits(String nid){
		sqlSession.update("mapper.notice.updateHits",nid);
	}
}
