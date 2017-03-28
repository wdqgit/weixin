<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<meta charset="utf-8"> 
	<title>病症搜索列表</title>
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
	<table class="table" style="width:600px;">
		<thead>
			<tr>
				<th >病症名称</th>
				<c:if test="${!empty admin }">
					<th >操作</th>
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${diseaseMessages }" var="disease">
			<tr>
				<td><a href="view.do?id=${disease.id }" class="list-group-item">${disease.title }</a></td>
				<c:if test="${!empty admin  }">
					<td>
						<a href="updateUI.do?id=${disease.id }" class="btn btn-primary" role="button">修改</a>
						<a href="delete.do?id=${disease.id }" class="btn btn-primary" role="button">删除</a>
					</td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	</div>
	
</div>

</body>
</html>