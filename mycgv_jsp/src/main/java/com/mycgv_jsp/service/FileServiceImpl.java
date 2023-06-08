package com.mycgv_jsp.service;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.mycgv_jsp.vo.BoardVo;

@Service("fileService")
public class FileServiceImpl {
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
		if(boardVo.getBfile() != null && !boardVo.getBfile().equals("")) {
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
