<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">  
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>

<frameset cols="17.3%,82.7%" framespacing="0" border="0" frameborder="0">
	<frame src="${pageContext.request.contextPath }/admin/left.do"/>
	<frameset rows="10%,90%," framespacing="0" border="0" frameborder="0">
		<frame src="${pageContext.request.contextPath }/admin/top.do"/>
		<frame name="content" src="${pageContext.request.contextPath }/admin/content.do"/>
	</frameset>

</frameset>

</html>
