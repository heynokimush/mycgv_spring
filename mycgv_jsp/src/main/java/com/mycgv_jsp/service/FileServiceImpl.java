package com.mycgv_jsp.service;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.mycgv_jsp.vo.BoardVo;
import com.mycgv_jsp.vo.NoticeVo;

@Service("fileService")
public class FileServiceImpl {
	/**
	 * multiFileCheck - ��Ƽ���� üũ ���
	 */
	public NoticeVo multiFileCheck(NoticeVo noticeVo) {
		for(CommonsMultipartFile file : noticeVo.getFiles()) {
			if(!file.getOriginalFilename().equals("")) {
				//���� ����
				UUID uuid = UUID.randomUUID();
				noticeVo.getNfiles().add(file.getOriginalFilename());
				noticeVo.getNsfiles().add(uuid+"_"+file.getOriginalFilename());
			} else {
				//���� ����
				noticeVo.getNfiles().add("");
				noticeVo.getNsfiles().add("");
			}
		}//for
		
		noticeVo.setNfile1(noticeVo.getNfiles().get(0));
		noticeVo.setNsfile1(noticeVo.getNsfiles().get(0));
		noticeVo.setNfile2(noticeVo.getNfiles().get(1));
		noticeVo.setNsfile2(noticeVo.getNsfiles().get(1));
		
		return noticeVo;
	}
	
	/**
	 * multiFileSave - ��Ƽ���� ���� ���
	 */
	public void multiFileSave(NoticeVo noticeVo, HttpServletRequest request) 
														throws Exception{
		//������ ������ġ
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "\\resources\\upload\\";
		
		//������ �����ϸ� ������ ����
		int count = 0;
		for(CommonsMultipartFile file : noticeVo.getFiles()) { //���� ��ü�� ������ �����ؾ��ϱ� ������ arrayList�� �ƴ� ���� �迭�� �۾��Ѵ�
			if(file.getOriginalFilename() != null 
					&& !file.getOriginalFilename().equals("")) {
				File saveFile = new File(root_path + attach_path + noticeVo.getNsfiles().get(count));
				file.transferTo(saveFile);
			}
			count++;
		}
	}
	
	/**
	 * multiFileDelete ��� - ���ο� ���� ���� �� ���� ������ �����ϴ� �޼ҵ�(������Ʈ)
	 */
	public void multiFileDelete(NoticeVo noticeVo, HttpServletRequest request, ArrayList<String> oldFileNames) 
														throws Exception{
		//������ ������ġ
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "\\resources\\upload\\";
		
		//���� ���� �������� ����
		int count = 0;
		for(CommonsMultipartFile file : noticeVo.getFiles()) { //���� ��ü�� ������ �����ؾ��ϱ� ������ arrayList�� �ƴ� ���� �迭�� �۾��Ѵ�
			if(!file.getOriginalFilename().equals("")) {
				File deleteFile = new File(root_path + attach_path + oldFileNames.get(count));
				if(deleteFile.exists()) {
					deleteFile.delete();
				}
			}
			count++;
		}
	}
	
	/**
	 * fileCheck ��� - ������ �����ϸ� boardVo�� bfile, bsfile set / ������ boardVo return
	 */
	/*
	 * �۾��� �� -> Controller(BoardVo) -> fileService.fileCheck(BoardVo)
	 * 									<-
	 */
	public BoardVo fileCheck(BoardVo boardVo) {
		if(boardVo.getFile1().getOriginalFilename() != null
				&& !boardVo.getFile1().getOriginalFilename().equals("")) {//������ �����ϸ�

			//bsfile ���ϸ� �ߺ� ó��
			UUID uuid = UUID.randomUUID();
			String bfile = boardVo.getFile1().getOriginalFilename();
			String bsfile = uuid + "_" + bfile;
			
			boardVo.setBfile(bfile);
			boardVo.setBsfile(bsfile);
			
		} else {
//			boardVo.setBfile("");
//			boardVo.setBsfile("");
		}
		
		return boardVo;
	}
	
	/**
	 * fileSave ��� - ������ �����ϸ� ������ �����ϴ� �޼ҵ�
	 */
	public void fileSave(BoardVo boardVo, HttpServletRequest request) 
														throws Exception{
		//������ ������ġ
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "\\resources\\upload\\";
		
		//������ �����ϸ� ������ ����
		if(boardVo.getFile1().getOriginalFilename() != null
				&& !boardVo.getFile1().getOriginalFilename().equals("")) {
			File saveFile = new File(root_path + attach_path + boardVo.getBsfile());
			boardVo.getFile1().transferTo(saveFile);
		}
	}
	
	/**
	 * fileDelete ��� - ���ο� ���� ���� �� ���� ������ �����ϴ� �޼ҵ�(������Ʈ)
	 */
	public void fileDelete(BoardVo boardVo, HttpServletRequest request, String oldFileName) 
														throws Exception{
		//������ ������ġ
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "\\resources\\upload\\";
		
		//���� ���� �������� ����
		if(!boardVo.getFile1().getOriginalFilename().equals("")) { //���ο� ���� ����
			File deleteFile = new File(root_path + attach_path + oldFileName);
			if(deleteFile.exists()) {
				deleteFile.delete();
			}
		}
	}
	
	/**
	 * fileDelete ��� - �Խñ� ���� �� ������ �����ϴ� �޼ҵ�
	 */
	public void fileDelete(HttpServletRequest request, String oldFileName) 
														throws Exception{
		//������ ������ġ
		String root_path = request.getSession().getServletContext().getRealPath("/");
		String attach_path = "\\resources\\upload\\";
		
		//���� ���� �������� ����
		if(oldFileName != null && !oldFileName.equals("")) {
			File deleteFile = new File(root_path + attach_path + oldFileName);
			if(deleteFile.exists()) {
				deleteFile.delete();
			}
		}
	}
}
