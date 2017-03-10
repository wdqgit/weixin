<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Bootstrap 实例 - 水平表单</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">  
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

</head>
<body>

<form action="sendText.do" method="post" class="form-horizontal" role="form">
	<textarea name="content" class="form-control" rows="3" placeholder="请输入正文"></textarea>
	<button type="submit" class="btn btn-primary">提交</button>
</form>

</body>
</html>