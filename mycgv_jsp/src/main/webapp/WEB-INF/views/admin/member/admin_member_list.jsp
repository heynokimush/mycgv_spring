<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--     
<%@ page import = "com.mycgv_jsp.dao.MemberDao" %>
<%@ page import = "com.mycgv_jsp.vo.MemberVo" %>
<%@ page import = "java.util.ArrayList" %>
<%
	String sid = (String)session.getAttribute("sid");
	if(sid == null){
		out.write("<script>");
		out.write("alert('접근 권한이 없습니다. 로그인을 진행해주세요.');");
		out.write("location.href = 'http://localhost:9000/mycgv_jsp/login/login.jsp'");
		out.write("</script>");
	} else {
		if(!sid.equals("admin")){
			out.write("<script>");
			out.write("alert('관리자 접근 권한이 필요한 페이지입니다.');");
			out.write("location.href = 'http://localhost:9000/mycgv_jsp/index.jsp'");
			out.write("</script>");
		} else {
			MemberDao memberDao = new MemberDao();
			ArrayList<MemberVo> list = memberDao.select();
%> --%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MYCGV</title>
<link rel="stylesheet" href="http://localhost:9000/mycgv_jsp/css/mycgv_jsp.css">
<link rel="stylesheet" href="http://localhost:9000/mycgv_jsp/css/am-pagination.css">
<script src="http://localhost:9000/mycgv_jsp/js/jquery-3.6.4.min.js"></script>
<script src="http://localhost:9000/mycgv_jsp/js/mycgv_jsp_jquery.js"></script>
<script src="http://localhost:9000/mycgv_jsp/js/am-pagination.js"></script>
<script>
	$(document).ready(function(){
		var pager = jQuery('#ampaginationsm').pagination({
		
		    maxSize: '${maxSize}',	    		// max page size
		    totals: '${totals}',	// total pages	
		    page: '${page}',		// initial page		
		    pageSize: '${pageSize}',			// max number items per page
		
		    // custom labels		
		    lastText: '&raquo;&raquo;', 		
		    firstText: '&laquo;&laquo;',		
		    prevText: '&laquo;',		
		    nextText: '&raquo;',
				     
		    btnSize:'sm'	// 'sm'  or 'lg'		
		});
		
		jQuery('#ampaginationsm').on('am.pagination.change',function(e){
			   jQuery('.showlabelsm').text('The selected page no: '+e.page);
	           $(location).attr('href', "http://localhost:9000/mycgv_jsp/admin_member_list.do?page="+e.page);         
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
		<section class="notice">
			<h1 class="title">관리자 - 회원관리</h1>			
			<table>
				<tr>
					<th>번호</th>
					<th>아이디</th>
					<th>성명</th>
					<th>가입일자</th>
					<th>회원등급</th>
				</tr>
				<c:forEach var="memberVo" items="${list}">
					<tr>
						<td>${memberVo.rno}</td>
						<td>${memberVo.id}</td>
						<td>${memberVo.name}</td>
						<td>${memberVo.mdate}</td>
						<td>${memberVo.grade}</td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="5"><div id="ampaginationsm"></div></td>
				</tr>
			</table>
		</section>
	</div>
	
	<!-- footer -->
	<!-- <iframe src="http://localhost:9000/mycgv_jsp/footer.jsp"
			scrolling="no" width="100%" height="500px" frameborder=0></iframe> -->	
	<jsp:include page="../../footer.jsp"></jsp:include>
</body>
</html>
<%-- <% 		}//admin check
	}//null check		
%> --%>















