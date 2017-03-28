<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>我的详细页面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>

<a href="javascript:window.history.back(-1);"><span style="font-size:30px;" class="glyphicon glyphicon-arrow-left"></span></a><br/>
	<table class="table table-hover">
		<thead>			
				<tr>
					<th>活动名称</th>
			<th>活动开始时间</th>
			<th>活动地点</th>
			<th>操作</th>
				</tr>
			</thead>
		<c:forEach items="${activitys }" var="activity">
			<tr><td>${activity.title }</td><td>${activity.begin_time }</td><td>${activity.location }</td>
			<td>
			<a href="${pageContext.request.contextPath }/activity/view.do?id=${activity.id }&type=people" class="btn btn-primary" role="button">查看详情</a>
			
			</td>
			</tr>
		</c:forEach>

	</table>

</body>
</html>