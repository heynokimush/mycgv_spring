package com.mycgv_jsp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mycgv_jsp.vo.BoardVo;

@Repository
public class BoardDao extends DBConn {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	/**
	 * insert - 게시글 등록
	 */
	public int insert(BoardVo boardVo) {
		return sqlSession.insert("mapper.board.write",boardVo);
//		int result = 0;
//		
//		String sql = "insert into mycgv_board(bid,btitle,bcontent,bhits,id,bdate,bfile,bsfile) "
//				+ "values('b_'||ltrim(to_char(sequ_mycgv_board_bid.nextval,'0000')),?,?,0,?,sysdate,?,?)";
//		getPreparedStatement(sql);
//		
//		try {
//			pstmt.setString(1, boardVo.getBtitle());
//			pstmt.setString(2, boardVo.getBcontent());
//			pstmt.setString(3, boardVo.getId());
//			pstmt.setString(4, boardVo.getBfile());
//			pstmt.setString(5, boardVo.getBsfile());
//			
//			result = pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
	}
	
	/**
	 * select - 게시글 전체 리스트
	 */
	public ArrayList<BoardVo> select() {
		List<BoardVo> list = sqlSession.selectList("mapper.board.list2");
		return (ArrayList<BoardVo>)list;
//		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
//		
//		String sql = "select rownum rno, bid, btitle, bcontent, bhits, id, to_char(bdate,'yyyy-mm-dd')\r\n" + 
//				"from (select bid, btitle, bcontent, bhits, id, bdate from mycgv_board\r\n" + 
//				"          order by bdate desc)";
//		getPreparedStatement(sql);
//		
//		try {
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				BoardVo boardVo = new BoardVo();
//				
//				boardVo.setRno(rs.getInt(1));
//				boardVo.setBid(rs.getString(2));
//				boardVo.setBtitle(rs.getString(3));
//				boardVo.setBcontent(rs.getString(4));
//				boardVo.setBhits(rs.getInt(5));
//				boardVo.setId(rs.getString(6));
//				boardVo.setBdate(rs.getString(7));
//				
//				list.add(boardVo);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return list;
	}
	
	/**
	 * select - 게시글 전체 리스트 -> 페이징
	 */
	public ArrayList<BoardVo> select(int startCount, int endCount) {
		Map<String,Integer> param = new HashMap<String,Integer>();
		param.put("start", startCount);
		param.put("end", endCount);
		
		List<BoardVo> list = sqlSession.selectList("mapper.board.list", param);
		
		return (ArrayList<BoardVo>)list;
//		ArrayList<BoardVo> list = new ArrayList<BoardVo>();
//		
//		String sql = "select rno, bid, btitle, bcontent, bhits, id, bdate"
//				+ " from (select rownum rno, bid, btitle, bcontent, bhits, id, to_char(bdate,'yyyy-mm-dd') bdate\r\n"  
//				+ " from (select bid, btitle, bcontent, bhits, id, bdate from mycgv_board\r\n"  
//				+ "          order by bdate desc))"
//				+ " where rno between ? and ?";
//		getPreparedStatement(sql);
//		
//		try {
//			pstmt.setInt(1, startCount);
//			pstmt.setInt(2, endCount);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				BoardVo boardVo = new BoardVo();
//				
//				boardVo.setRno(rs.getInt(1));
//				boardVo.setBid(rs.getString(2));
//				boardVo.setBtitle(rs.getString(3));
//				boardVo.setBcontent(rs.getString(4));
//				boardVo.setBhits(rs.getInt(5));
//				boardVo.setId(rs.getString(6));
//				boardVo.setBdate(rs.getString(7));
//				
//				list.add(boardVo);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return list;
	}
	
	/**
	 * select - 게시글 상세
	 */
	public BoardVo select(String bid) {
		return sqlSession.selectOne("mapper.board.content", bid);
//		BoardVo boardVo = new BoardVo();
//		
//		String sql = "select bid, btitle, bcontent, bhits, id, bdate, bfile, bsfile from mycgv_board\r\n"
//				+ "where bid=?";
//		getPreparedStatement(sql);
//		
//		try {
//			pstmt.setString(1, bid);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				boardVo.setBid(rs.getString(1));
//				boardVo.setBtitle(rs.getString(2));
//				boardVo.setBcontent(rs.getString(3));
//				boardVo.setBhits(rs.getInt(4));
//				boardVo.setId(rs.getString(5));
//				boardVo.setBdate(rs.getString(6));
//				boardVo.setBfile(rs.getString(7));
//				boardVo.setBsfile(rs.getString(8));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return boardVo;
	}
	
	/**
	 * update - 게시글 수정
	 */
	public int update(BoardVo boardVo) {
		return sqlSession.update("mapper.board.update", boardVo);
//		int result = 0;
//		
//		String sql = "update mycgv_board set btitle=?, bcontent=? where bid=?";
//		getPreparedStatement(sql);
//		
//		try {
//			pstmt.setString(1, boardVo.getBtitle());
//			pstmt.setString(2, boardVo.getBcontent());
//			pstmt.setString(3, boardVo.getBid());
//			
//			result = pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
	}
	
	/**
	 * updateHits - 게시글 수정
	 */
	public void updateHits(String bid) {
		sqlSession.update("mapper.board.updateHits",bid);
//		int result = 0;
//		
//		String sql = "update mycgv_board set bhits=bhits+1 where bid=?";
//		getPreparedStatement(sql);
//		
//		try {
//			pstmt.setString(1, bid);
//			
//			result = pstmt.executeUpdate();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return result;
	}
	
	/**
	 * delete - 게시글 삭제
	 */
	public int delete(String bid) {
		return sqlSession.delete("mapper.board.delete",bid);
//		int result = 0;
//		
//		String sql = "delete from mycgv_board where bid=?";
//		getPreparedStatement(sql);
//		
//		try {
//			pstmt.setString(1, bid);
//			
//			result = pstmt.executeUpdate();
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
			String sql = "select count(*) from mycgv_board";
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
