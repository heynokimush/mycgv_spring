package com.mycgv_jsp.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.mycgv_jsp.dao.BoardDao;
import com.mycgv_jsp.vo.BoardVo;

@Service
public class BoardServiceImpl implements BoardService{
	BoardDao boardDao = new BoardDao();
	
	@Override
	public int getCount() {
		return boardDao.totalRowCount();
	}
	
	@Override
	public ArrayList<BoardVo> getSelect(int startCount, int endCount) {
		return boardDao.select(startCount, endCount);
	}
	
	@Override
	public BoardVo getSelect(String bid) {
		return boardDao.select(bid);
	}
	
	@Override
	public int getUpdateHits(String bid) {
		return boardDao.updateHits(bid);
	}
	
	@Override
	public int getInsert(BoardVo boardVo) {
		return boardDao.insert(boardVo);
	}
	
	@Override
	public int getUpdate(BoardVo boardVo) {
		return boardDao.update(boardVo);
	}
	
	@Override
	public int getDelete(String bid) {
		return boardDao.delete(bid);
	}
}