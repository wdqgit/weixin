<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">  
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>
<table class="table table-hover">
	<thead>
		<tr><td>序号</td>
		<td>管理员名称</td>
		<td>手机号</td>
		<td>住址</td>
		<td>操作</td></tr>
	</thead>
	<tbody>
	<c:forEach items="${admins }" var="staff" varStatus="status">
		<tr><td>${status.index + 1 }</td>
		<td>${staff.name }</td>
		<td>${staff.phone }</td>
		<td>${staff.address }</td>
		<td>
		<c:if test="${admin.name  == 'admin'}">
			<a href="deleteAdmin.do?id=${staff.id }"  onclick="javascript:return confirm('按下按钮')" class="btn btn-primary" role="button">删除</a>
		</c:if>
		
		</td></tr>
		
	</c:forEach>
	</tbody>
</table>

</body>
</html>