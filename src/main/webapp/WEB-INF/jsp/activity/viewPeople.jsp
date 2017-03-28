<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">  
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

</head>
<body>
	<div style="padding-top:10px;">
<span class="lead">${activity.title }  活动的参与人员</span>

</div>


<table class="table table-hover">


	<thead>
		<tr>
			<th>名称</th>
			<th>手机号</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${activity.peoples }" var="people">
			<tr>
				<td>${people.name }</td><td>${people.phone }</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

</body>
</html>