<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<meta charset="utf-8"> 
	<title>义诊历届活动</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">  
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>

<div style="padding-top: 20px;">
	<table class="table table-hover">
		<thead>
			<tr><th>活动标题</th><th>活动时间</th></tr>
		</thead>
		<tbody>
			<c:forEach items="${pageBean.recordList }" var="history">
				<tr>
					<td><a href="view.do?id=${history.id }">${history.title }</a></td>
					<td>${history.time }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<ul class="pagination pagination-lg">
	<li>
		<c:if test="${pageBean.currentPage <= 1 }"><a href="#" class="disabled">&laquo;</a></c:if>
		<c:if test="${pageBean.currentPage > 1 }">
			<a href="list.do?currentPage=${pageBean.currentPage - 1 }">&laquo;</a>
		</c:if>
	</li>
	<c:forEach begin="${pageBean.beginPageIndex }" end="${pageBean.endPageIndex }" varStatus="status">
		<li <c:if test="${pageBean.currentPage == (status.index) }">class="active"</c:if>><a href="list.do?currentPage=${status.index }">${status.index }</a></li>
	</c:forEach>
	<li>
		<c:if test="${pageBean.currentPage >= pageBean.pageCount }"><a href="#" class="disabled">&raquo;</a></c:if>
		<c:if test="${pageBean.currentPage < pageBean.pageCount }">
			<a href="list.do?currentPage=${pageBean.currentPage + 1 }">&raquo;</a>
		</c:if>
	</li>
</ul>
	
</div>

</body>
</html>