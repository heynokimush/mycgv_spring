<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9000/mycgv_jsp/css/mycgv_jsp.css">
<script src="http://localhost:9000/mycgv_jsp/js/jquery-3.6.4.min.js"></script>
<script src="http://localhost:9000/mycgv_jsp/js/mycgv_jsp_jquery.js"></script>
<style>
	.update_file {
		border:1px solid white;
		position:relative;
		left:118px; top:-30px;
		background:white;
		display:inline-block;
		text-align:left;
		width:200px;
		padding-left:0px;
	}
</style>
<script>
	$(document).ready(function(){
		$("#file1").change(function(){
			if(window.FileReader){
				let fname = $(this)[0].files[0].name;
				$("#update_file1").text(fname);
			}
		});
		$("#file2").change(function(){
			if(window.FileReader){
				let fname = $(this)[0].files[0].name;
				$("#update_file2").text(fname);
			}
		});
	});
</script>
</head>
<body>
	<!-- header -->
	<!-- <iframe src="http://localhost:9000/mycgv_jsp/header.jsp"
			scrolling="no" width="100%" height="149px" frameborder=0></iframe> -->
	<jsp:include page="../../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="board">
			<h1 class="title">관리자 - 공지사항</h1>
			<form name="updateForm" action="admin_notice_update_proc.do" method="post" enctype="multipart/form-data">
				<table>
					<tr>
						<th>제목</th>
						<td>
							<input type="text" name="ntitle" id="ntitle" value="${noticeVo.ntitle}">
						</td>
					</tr>
					<tr>
						<th>내용</th>
						<td>
							<textarea name="ncontent" id="ncontent">${noticeVo.ncontent}</textarea>
						</td>
					</tr>
					<tr>
						<th>파일업로드</th>
						<td>
							<input type="hidden" name="nfile1" value="${noticeVo.nfile1}">
							<input type="hidden" name="nsfile1" value="${noticeVo.nsfile1}">
							<input type="file" name="files" id="file1">
							<c:choose>
								<c:when test="${noticeVo.nfile1 != null}">
									<span id="update_file1" class="update_file">${noticeVo.nfile1}</span>
								</c:when>
								<c:otherwise>
									<span id="update_file1" class="update_file">선택된 파일 없음</span>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>
					<tr>
						<th>파일업로드</th>
						<td>
							<input type="hidden" name="nfile2" value="${noticeVo.nfile2}">
							<input type="hidden" name="nsfile2" value="${noticeVo.nsfile2}">
							<input type="file" name="files" id="file2">
							<c:choose>
								<c:when test="${noticeVo.nfile2 != null}">
									<span id="update_file2" class="update_file">${noticeVo.nfile2}</span>
								</c:when>
								<c:otherwise>
									<span id="update_file2" class="update_file">선택된 파일 없음</span>
								</c:otherwise>
							</c:choose>
						</td>
					</tr>					
					<tr>
						<td colspan="2">
							<input type="hidden" name="nid" value="${noticeVo.nid}">
							<button type="button" id="btnNoticeUpdate">수정완료</button>
							<button type="reset">다시쓰기</button>
							<a href="admin_notice_content.do?nid=${noticeVo.nid}">
								<button type="button">이전페이지</button></a>
							<a href="admin_notice_list.do">
								<button type="button">리스트</button></a>							
						</td>				
					</tr>
				</table>
			</form>
		</section>
	</div>
	
	<!-- footer -->
	<!-- <iframe src="http://localhost:9000/mycgv_jsp/footer.jsp"
			scrolling="no" width="100%" height="500px" frameborder=0></iframe> -->	
	<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>

















