<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<meta charset="utf-8"> 
	<title>历届活动详细页面</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">  
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body style="background-color:#E8E8D0;">
<a href="javascript:window.history.back(-1);"><span style="font-size:30px;" class="glyphicon glyphicon-arrow-left"></span></a>
<div style="padding:0px 20px 0px 20px;">
	<h1 class="text-center" >${historyMessage.title }</h1>
	<p class="lead text-justify">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${historyMessage.content }</p>
	<h3>时间</h3>
	<p class="lead text-justify">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${historyMessage.time }</p>

</div>







</body>
</html>