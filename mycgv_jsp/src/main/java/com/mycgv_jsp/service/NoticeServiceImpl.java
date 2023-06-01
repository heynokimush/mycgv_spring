package com.mycgv_jsp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mycgv_jsp.dao.NoticeDao;
import com.mycgv_jsp.vo.NoticeVo;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeDao noticeDao;
	
	@Override
	public ArrayList<NoticeVo> getSelect(int startCount, int endCount){
		return noticeDao.select(startCount, endCount);
	};
	
	@Override
	public NoticeVo getSelect(String nid) {
		return noticeDao.select(nid);
	};
	
	@Override
	public void getUpdateHits(String nid) {
		noticeDao.updateHits(nid);
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
