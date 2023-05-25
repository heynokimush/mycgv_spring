package com.mycgv_jsp.service;

import java.util.ArrayList;

import com.mycgv_jsp.dao.NoticeDao;
import com.mycgv_jsp.vo.NoticeVo;

public class NoticeServiceImpl implements NoticeService{
	NoticeDao noticeDao = new NoticeDao();
	
	@Override
	public int getCount() {
		return noticeDao.totalRowCount();
	};
	
	@Override
	public ArrayList<NoticeVo> getSelect(int startCount, int endCount){
		return noticeDao.select(startCount, endCount);
	};
	
	@Override
	public NoticeVo getSelect(String nid) {
		return noticeDao.select(nid);
	};
	
	@Override
	public int getUpdateHits(String nid) {
		return noticeDao.updateHits(nid);
	};
	
	@Override
	public int getInsert(NoticeVo noticeVo) {
		return noticeDao.insert(noticeVo);
	};
	
	@Override
	public int getUpdate(NoticeVo noticeVo) {
		return noticeDao.update(noticeVo);
	};
	
	@Override
	public int getDelete(String nid) {
		return noticeDao.delete(nid);
	};
}
