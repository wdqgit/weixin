<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
	<meta charset="UTF-8">
<title>注册界面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>
	<form class="form-horizontal" role="form" action="register.do" method="post">
		<div class="form-group">
			<label for="firstname" class="col-sm-2 control-label">手机号：</label>
			<div class="col-sm-10">
				<input type="text" name="name" class="form-control" id="firstname"
					placeholder="请输入手机号">
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">姓名：</label>
			<div class=" col-xs-10" id="lastname">
				<input type="text" name="idCode" class="form-control"
					placeholder="请输入姓名"><br />
			</div>

		</div>
		<div class="form-group">
			<label for="firstname" class="col-sm-2 control-label">家庭住址：</label>
			<div class="col-sm-10">
				<input type="text" name="address" class="form-control" id="firstname"
					placeholder="请输入住址">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-lg btn-block">注册</button>
			</div>
		</div>
	</form>
</body>
</html>