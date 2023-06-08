package com.mycgv_jsp.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.mycgv_jsp.vo.BoardVo;

@Service("fileService")
public class FileServiceImpl {
	/**
	 * fileCheck 기능 - 파일이 존재하면 boardVo에 bfile, bsfile set / 없으면 boardVo return
	 */
	/*
	 * 글쓰기 폼 -> Controller(BoardVo) -> fileService.fileCheck(BoardVo)
	 * 									<-
	 */
	public BoardVo fileCheck(BoardVo boardVo) {
		if(boardVo.getFile1().getOriginalFilename() != null
				&& !boardVo.getFile1().getOriginalFilename().equals("")) {//파일이 존재하면

			//bsfile 파일명 중복 처리
			UUID uuid = UUID.randomUUID();
			String bfile = boardVo.getFile1().getOriginalFilename();
			String bsfile = uuid + "_" + bfile;
			
			System.out.println(bfile);
			System.out.println(bsfile);
			
			boardVo.setBfile(bfile);
			boardVo.setBsfile(bsfile);
			
		} else {
			System.out.println("파일 없음");
			boardVo.setBfile("");
			boardVo.setBsfile("");
		}
		
		return boardVo;
	}
}
