package com.mycgv_jsp.service;

import java.util.ArrayList;

import com.mycgv_jsp.vo.BoardVo;

public interface BoardService {
	public ArrayList<BoardVo> getSelect(int startCount, int endCount);
	public BoardVo getSelect(String bid);
	public void getUpdateHits(String bid);
	public int getInsert(BoardVo boardVo);
	public int getUpdate(BoardVo boardVo);
	public int getDelete(String bid);
}
