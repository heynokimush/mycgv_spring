<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9000/mycgv/css/mycgv.css">
</head>
<body>
	<!-- header -->
	<!-- <iframe src="http://localhost:9000/mycgv_jsp_jsp_jsp/header.jsp"
			scrolling="no" width="100%" height="149px" frameborder=0></iframe> -->
	<jsp:include page="../../header.jsp"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="board">
			<h1 class="title">관리자 - 공지사항</h1>			
			<table class="board_list">
				<tr>
					<td colspan="4">
						<a href="admin_notice_write.jsp">
							<button type="button">등록하기</button>
						</a>
					</td>
				</tr>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>조회수</th>
					<th>작성일자</th>
				</tr>
				<tr>
					<td>1</td>
					<td><a href="admin_notice_content.jsp">CGV 공지사항 입니다.</a></td>
					<td>1000</td>
					<td>2023-04-18</td>
				</tr>
				<tr>
					<td>2</td>
					<td>CGV 공지사항 입니다.</td>
					<td>1000</td>
					<td>2023-04-18</td>
				</tr>
				<tr>
					<td>3</td>
					<td>CGV 공지사항 입니다.</td>
					<td>1000</td>
					<td>2023-04-18</td>
				</tr>
				<tr>
					<td>4</td>
					<td>CGV 공지사항 입니다.</td>
					<td>1000</td>
					<td>2023-04-18</td>
				</tr>
				<tr>
					<td>5</td>
					<td>CGV 공지사항 입니다.</td>
					<td>1000</td>
					<td>2023-04-18</td>
				</tr>
				<tr>
					<td>6</td>
					<td>CGV 공지사항 입니다.</td>
					<td>1000</td>
					<td>2023-04-18</td>
				</tr>
				<tr>
					<td>7</td>
					<td>CGV 공지사항 입니다.</td>
					<td>1000</td>
					<td>2023-04-18</td>
				</tr>
				<tr>
					<td>8</td>
					<td>CGV 공지사항 입니다.</td>
					<td>1000</td>
					<td>2023-04-18</td>
				</tr>
				<tr>
					<td>9</td>
					<td>CGV 공지사항 입니다.</td>
					<td>1000</td>
					<td>2023-04-18</td>
				</tr>
				<tr>
					<td>10</td>
					<td>CGV 공지사항 입니다.</td>
					<td>1000</td>
					<td>2023-04-18</td>
				</tr>
				<tr>
					<td colspan="4"><< 1  2  3  4  5 >></td>
				</tr>
			</table>
		</section>
	</div>
	
	<!-- footer -->
	<!-- <iframe src="http://localhost:9000/mycgv_jsp_jsp_jsp/footer.jsp"
			scrolling="no" width="100%" height="500px" frameborder=0></iframe> -->	
	<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>
















