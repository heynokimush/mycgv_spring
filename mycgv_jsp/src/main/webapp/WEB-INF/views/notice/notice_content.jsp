<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9000/mycgv_jsp/css/mycgv_jsp.css">
</head>
<body>
	<!-- header -->
	<!-- <iframe src="http://localhost:9000/mycgv_jsp_jsp/header.jsp"
			scrolling="no" width="100%" height="149px" frameborder=0></iframe> -->
	<jsp:include page="../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="notice">
			<h1 class="title">공지사항</h1>
			<table class="notice_content">
				<tr>
					<th>제목</th>
					<td>CGV 공지사항 입니다.</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>
						2023년 4월 기준 전체 시스템 점검 예정입니다.<br>
						참고해주세요.
					</td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>1000</td>
				</tr>
				<tr>
					<th>작성일자</th>
					<td>2023-04-18</td>
				</tr>
				<tr>
					<td colspan="2">
						<a href="notice_list.jsp">
							<button type="button" class="btn_style">리스트</button></a>
						<a href="http://localhost:9000/mycgv_jsp/index.jsp">
						<button type="button" class="btn_style">홈으로</button></a>
					</td>
				</tr>
			</table>
		</section>
	</div>
	
	<!-- footer -->
	<!-- <iframe src="http://localhost:9000/mycgv_jsp_jsp/footer.jsp"
			scrolling="no" width="100%" height="500px" frameborder=0></iframe> -->	
	<jsp:include page="../footer.jsp"></jsp:include>
</body>
</html>
















