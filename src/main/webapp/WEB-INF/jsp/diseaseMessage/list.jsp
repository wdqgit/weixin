<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<meta charset="utf-8"> 
	<title>病症列表</title>
	<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">  
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>

<div style="padding-top: 20px;">
	<form action="find.do" class="bs-example bs-example-form" role="form">
			<div class="col-lg-6">
				<div class="input-group">
					<input type="text" name="key" class="form-control">
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
		<a class="list-group-item active">病症名称</a>
		<c:forEach items="${pageBean.recordList }" var="disease">
			<a href="view.do?id=${disease.id }" class="list-group-item">${disease.title }</a>
		</c:forEach>
	</div>
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