<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<meta charset="utf-8"> 
	<title>Bootstrap 实例 - 水平表单</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">  
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>

<div style="padding-top: 20px;">
	<form action="find.do" class="bs-example bs-example-form" role="form">
			<div class="col-lg-6">
				<div class="input-group">
					<input name="key" type="text" class="form-control">
					<span class="input-group-btn">
						<button class="btn btn-default" type="submit">
							Go!
						</button>
					</span>
				</div><!-- /input-group -->
			</div><!-- /.col-lg-6 -->
	</form>
	<br/>
	<div style="padding: 30px 0px 0px">
	<table class="table" ">
		<thead>
			<tr>
				<th >标题</th>
				<th>时间</th>
				<th >操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${pageBean.recordList }" var="history">
			<tr>
				<td>${history.title }</td>
				<td>${history.time }</td>
				<td>
					<a href="updateUI.do?id=${history.id }" class="btn btn-primary" role="button">修改</a>
					<a href="delete.do?id=${history.id }" class="btn btn-primary" role="button">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	<ul class="pagination pagination-lg">
	<li>
		<c:if test="${pageBean.currentPage <= 1 }"><a href="#" class="disabled">&laquo;</a></c:if>
		<c:if test="${pageBean.currentPage > 1 }">
			<a href="adminList.do?currentPage=${pageBean.currentPage - 1 }">&laquo;</a>
		</c:if>
	</li>
	<c:forEach begin="${pageBean.beginPageIndex }" end="${pageBean.endPageIndex }" varStatus="status">
		<li <c:if test="${pageBean.currentPage == (status.index) }">class="active"</c:if>><a href="adminList.do?currentPage=${status.index }">${status.index }</a></li>
	</c:forEach>
	<li>
		<c:if test="${pageBean.currentPage >= pageBean.pageCount }"><a href="#" class="disabled">&raquo;</a></c:if>
		<c:if test="${pageBean.currentPage < pageBean.pageCount }">
			<a href="adminList.do?currentPage=${pageBean.currentPage + 1 }">&raquo;</a>
		</c:if>
	</li>
</ul>
	
</div>

</body>
</html>