<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>
	<table class="table table-hover">
		<tbody>
			<c:if test="${type == 'admin' }">
				<tr><td><span class="lead"><a
			href="${pageContext.request.contextPath }/activity/list.do?type=${type }"><span
				class="glyphicon glyphicon-arrow-left"></span></a></span></td>
					<td><a href="updateUI.do?id=${admin.id }&type=admin"
						class="btn btn-primary" role="button">修改</a></td>
				</tr>
				<tr>
					<td>管理员姓名：</td>
					<td>${admin.name }</td>
				</tr>
				<tr>
					<td>手机号：</td>
					<td>${admin.phone }</td>
				</tr>
				<tr>
					<td>地址：</td>
					<td>${admin.address }</td>
				</tr>


			</c:if>
			<c:if test="${type == 'people' }">
				<tr><td><span class="lead"><a
			href="${pageContext.request.contextPath }/activity/list.do?type=${type }"><span
				class="glyphicon glyphicon-arrow-left"></span></a></span></td>
					<td><a href="updateUI.do?id=${people.id }&type=people"
						class="btn btn-primary" role="button">修改</a></td>
				</tr>
				<tr>
					<td>姓名：</td>
					<td>${people.name }</td>
				</tr>
				<tr>
					<td>性别：</td>
					<td><c:if test="${people.sex == 'MAN' }">男</c:if> <c:if
							test="${people.sex == 'WOMAN' }">女</c:if></td>
				</tr>
				<tr>
					<td>地址：</td>
					<td>${people.address }</td>
				</tr>
				<tr>
					<td>手机号：</td>
					<td>${people.phone }</td>
				</tr>
			</c:if>

		</tbody>

	</table>

</body>
</html>