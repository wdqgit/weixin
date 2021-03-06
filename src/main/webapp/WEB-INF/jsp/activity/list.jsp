<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>义诊活动列表</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">  
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body >
	

	<div style="padding-top:10px;">
<span class="lead">义诊活动列表</span>
<span class="" style="padding-left:49.5%;">
	<c:if test="${type == 'people' }"><a href="${pageContext.request.contextPath }/people/view.do?id=${people.id}&type=people">${people.phone }</a></c:if>
	
</span>
</div>
<table class="table table-hover">


	<thead>
		<tr>
			<th>名称</th>
			<th>开始时间</th>
			<th colspan="2">操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${activitys }" var="activity">
			<tr><td>${activity.title }</td><td>${fn:split(activity.begin_time, " ")[0] }</td>
			<td><c:if test="${type == 'admin' }"><a href="delete.do?id=${activity.id }">取消活动</a></c:if>
			<c:if test="${type == 'people' }">
				<c:forEach items="${people.activitys }" var="p_activity">
					
					<c:if test="${p_activity.id == activity.id }"><c:set var="p_a_type" value="${activity.id }"/><a href="#" class="btn btn-primary disabled" role="button">已参加</a></c:if>
					
				</c:forEach>
				<c:choose>
					<c:when test="${fn:length(activity.peoples) < activity.peopleNum }"><c:if test="${p_a_type != activity.id}"><a href="addPeople.do?peopleid=${people.id }&activityid=${activity.id}" class="btn btn-primary" role="button">参加</a></c:if>
					</c:when>
					<c:otherwise><a href="#" class="btn btn-default btn-lg disabled" role="button">人数已满</a></c:otherwise>
				</c:choose>
			</c:if>
			<a href="view.do?id=${activity.id }&type=${type}" class="btn btn-primary" role="button">详情</a>
			<c:if test="${!empty admin }">
				<a href="viewPeople.do?id=${activity.id }" class="btn btn-primary" role="button">参加人员</a>
			</c:if>
			
			</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
	
	

</body>
</html>