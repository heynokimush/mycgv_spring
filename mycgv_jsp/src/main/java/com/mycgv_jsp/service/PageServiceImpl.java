package com.mycgv_jsp.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

public class PageServiceImpl {
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private NoticeService noticeService;

	public Map<String, Integer> getPageResult(String page, String serviceName) {
		Map<String, Integer> param = new HashMap<String, Integer>();
		
		//����¡ ó�� - startCount, endCount ���ϱ�
		int startCount = 0;
		int endCount = 0;
		int pageSize = 10;	//���������� �Խù� ��
		int reqPage = 1;	//��û������	
		int pageCount = 1;	//��ü ������ ��
		int dbCount = 0;	//DB���� ������ ��ü ���
		
		if(serviceName.equals("notice")) {
			//�Ű����� serviceType�� noticeService ��ȯ
			//noticeService = (NoticeService)serviceType; //������ �ڵ�, ����ȯ�� �̷������� �����ϸ�ȴ�
			dbCount = noticeService.getCount();
		} else if(serviceName.equals("member")) {
			dbCount = memberService.getCount();
		} else if(serviceName.equals("board")) {
			dbCount = boardService.getCount();
		}
		
		//�� ������ �� ���
		if(dbCount % pageSize == 0){
			pageCount = dbCount/pageSize;
		}else{
			pageCount = dbCount/pageSize+1;
		}

		//��û ������ ���
		if(page != null){
			reqPage = Integer.parseInt(page);
			startCount = (reqPage-1) * pageSize+1; 
			endCount = reqPage *pageSize;
		}else{
			startCount = 1;
			endCount = pageSize;
		}
		
		//param ��ü�� ������ �ֱ�
		param.put("startCount", startCount);
		param.put("endCount", endCount);
		param.put("totals", dbCount);
		param.put("pageSize", pageSize);
		param.put("maxSize", pageCount);
		param.put("page", reqPage);
		
		return param;
	}
}