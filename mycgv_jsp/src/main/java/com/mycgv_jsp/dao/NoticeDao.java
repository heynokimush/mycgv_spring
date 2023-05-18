package com.mycgv_jsp.dao;

import java.util.ArrayList;

import com.mycgv_jsp.vo.NoticeVo;

public class NoticeDao extends DBConn {
	/**
	 * select - 공지사항 전체 리스트
	 */
	public ArrayList<NoticeVo> select(){
		ArrayList<NoticeVo> list = new ArrayList<NoticeVo>();
		
		String sql = "select rownum rno,nid,ntitle,ncontent,nhits,to_char(ndate,'yyyy-mm-dd')\r\n" + 
				"from(select nid,ntitle,ncontent,nhits,ndate from mycgv_notice order by ndate desc)";
		getPreparedStatement(sql);
		
		try {
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				NoticeVo noticeVo = new NoticeVo();
				noticeVo.setRno(rs.getInt(1));
				noticeVo.setNid(rs.getString(2));
				noticeVo.setNtitle(rs.getString(3));
				noticeVo.setNcontent(rs.getString(4));
				noticeVo.setNhits(rs.getInt(5));
				noticeVo.setNdate(rs.getString(6));
				
				list.add(noticeVo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	/**
	 * select - 공지사항 상세
	 */
	public NoticeVo select(String nid){
		NoticeVo noticeVo = new NoticeVo();
		
		String sql = "select nid,ntitle,ncontent,nhits,ndate from mycgv_notice where nid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, nid);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				noticeVo.setNid(rs.getString(1));
				noticeVo.setNtitle(rs.getString(2));
				noticeVo.setNcontent(rs.getString(3));
				noticeVo.setNhits(rs.getInt(4));
				noticeVo.setNdate(rs.getString(5));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return noticeVo;
	}
	
	/**
	 * insert - 공지사항 글쓰기
	 */
	public int insert(NoticeVo noticeVo){
		int result = 0;
		
		String sql = "insert into mycgv_notice(nid,ntitle,ncontent,nhits,ndate)\r\n" + 
				"values('n_'||trim(to_char(sequ_mycgv_notice_nid.nextval,'0000')),?,?,0,sysdate)";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, noticeVo.getNtitle());
			pstmt.setString(2, noticeVo.getNcontent());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * update - 공지사항 수정
	 */
	public int update(NoticeVo noticeVo){
		int result = 0;
		
		String sql = "update mycgv_notice set ntitle=?, ncontent=? where nid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, noticeVo.getNtitle());
			pstmt.setString(2, noticeVo.getNcontent());
			pstmt.setString(3, noticeVo.getNid());
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * update - 공지사항 수정
	 */
	public int delete(String nid){
		int result = 0;
		
		String sql = "delete from mycgv_notice where nid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, nid);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	/**
	 * updateHits - 공지사항 조회수 수정
	 */
	public int updateHits(String nid){
		int result = 0;
		
		String sql = "update mycgv_notice set nhits=nhits+1 where nid=?";
		getPreparedStatement(sql);
		
		try {
			pstmt.setString(1, nid);
			
			result = pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
