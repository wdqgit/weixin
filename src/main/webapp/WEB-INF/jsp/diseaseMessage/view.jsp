<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8"> 
	<title>Bootstrap 实例 - 水平表单</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">  
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body style="background-color:#E8E8D0;">
<a href="javascript:window.history.back(-1);"><span style="font-size:30px;" class="glyphicon glyphicon-arrow-left"></span></a>
<div style="padding:0px 20px 0px 20px;">
	<h3>病症名称</h3>
	<p class="lead text-justify">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${diseaseMessage.title }</p>
	<h3>病症描述</h3>
	<p class="lead text-justify">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${diseaseMessage.content }</p>
	<h3>解决建议</h3>
	<p class="lead text-justify">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${diseaseMessage.solution }</p>

</div>







</body>
</html>