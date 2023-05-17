<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9000/mycgv_jsp/css/mycgv_jsp.css">
<script src="http://localhost:9000/mycgv_jsp/js/jquery-3.6.4.min.js"></script>
<script src="http://localhost:9000/mycgv_jsp/js/mycgv_jsp_jquery.js"></script>
</head>
<body>
	<!-- header -->
	<jsp:include page="/header.do"></jsp:include>
	
	<!-- content -->
	<div class="content">
		<section class="login">
			<h1 class="title">로그인 실패</h1>
				<img src="https://cdn.wpbeginner.com/wp-content/uploads/2016/03/403forbiddenerror.jpg">
				<a href="http://localhost:9000/mycgv_jsp/login.do">
					<button type="button" class="btn_style">로그인 페이지 이동</button>
				</a>
		</section>
	</div>
	
	<!-- footer -->
	<jsp:include page="/footer.do"></jsp:include>
</body>
</html>
















