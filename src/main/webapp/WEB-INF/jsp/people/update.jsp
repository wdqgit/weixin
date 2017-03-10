<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
	
	<c:if test="${type == 'admin' }">
		<form:form action="update.do" method="post" commandName="admin"
			class="form-horizontal" role="form">
			<input type="hidden" name="type" value="admin" />
			<form:hidden path="id" />
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">手机号：</label>
				<div class="col-sm-10">
					<form:input class="form-control" id="firstname" path="phone" />
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">姓名：</label>
				<div class=" col-xs-10" id="lastname">
					<form:input class="form-control" path="name" />
					<br />
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">家庭住址：</label>
				<div class=" col-xs-10" id="lastname">
					<form:input path="address" class="form-control" />
					<br />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-lg btn-block">保存</button>
					<button type="button" class="btn btn-primary btn-lg btn-block" onclick="javascript:window.history.back();">返回</button>
				</div>
			</div>
		</form:form>
	</c:if>
	<c:if test="${type == 'people' }">
		<form:form action="update.do" method="post" commandName="people"
			class="form-horizontal" role="form">
			<input type="hidden" name="type" value="people" />
			<form:hidden path="id" />
			<div class="form-group">
				<label for="firstname" class="col-sm-2 control-label">手机号：</label>
				<div class="col-sm-10">
					<form:input class="form-control" id="firstname" path="phone" />
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">姓名：</label>
				<div class=" col-xs-10" id="lastname">
					<form:input class="form-control" path="name" />
					<br />
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">性别：</label>
				<div class=" col-xs-10" id="lastname">
					<form:select path="sex" class="form-control">
						<form:option value="MAN">男</form:option>
						<form:option value="WOMAN">女</form:option>
					</form:select>
				</div>
			</div>
			<div class="form-group">
				<label for="lastname" class="col-sm-2 control-label">家庭住址：</label>
				<div class=" col-xs-10" id="lastname">
					<form:input path="address" class="form-control" />
					<br />
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-primary btn-lg btn-block">保存</button>
					<button type="button" class="btn btn-primary btn-lg btn-block" onclick="javascript:window.history.back();">返回</button>
				</div>
			</div>
		</form:form>
	</c:if>
</body>
</html>