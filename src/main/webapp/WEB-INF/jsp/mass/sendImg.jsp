<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<title>Insert title here</title>
	<meta charset="utf-8"> 
	<title>Bootstrap 实例 - 水平表单</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">  
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

</head>
<body>
<form action="sendImg.do" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
	
	图片： <input type="file" name="file" class="form-control" id="firstname"><br/>
	<button type="submit" class="btn btn-primary">提交</button>
	


</form>


</body>
</html>