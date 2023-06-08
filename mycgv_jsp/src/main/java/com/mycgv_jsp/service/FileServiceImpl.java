package com.mycgv_jsp.service;

import java.util.UUID;

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
			
			System.out.println(bfile);
			System.out.println(bsfile);
			
			boardVo.setBfile(bfile);
			boardVo.setBsfile(bsfile);
			
		} else {
			System.out.println("���� ����");
			boardVo.setBfile("");
			boardVo.setBsfile("");
		}
		
		return boardVo;
	}
}
