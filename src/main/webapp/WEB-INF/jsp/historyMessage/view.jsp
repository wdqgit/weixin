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
	<h3>时间</h3>
	<p class="lead text-justify">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${historyMessage.title }</p>
	<h3>内容</h3>
	<p class="lead text-justify">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${historyMessage.content }</p>
	<h3>时间</h3>
	<p class="lead text-justify">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${historyMessage.solution }</p>

</div>







</body>
</html>