<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>

<div style="width:500px;border: solid 1px black;float:left;height:500px;">
	<a href="createQRCode.do" class="btn btn-primary" role="button">创建临时二维码</a>
	<img alt="" src="${path }">
</div>
<div style="width:500px;border: solid 1px black;float:left;height:500px;">
	<a href="createYJQRCode.do" class="btn btn-primary" role="button">创建永久二维码</a>
	<img alt="" src="${yjpath }">
</div>

</body>
</html>