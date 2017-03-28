<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>
	<div style="padding-top: 10px;">
		<span class="lead"><a href="list.do?type=admin"><span
				class="glyphicon glyphicon-arrow-left"></span></a></span>
	</div>
	<form class="form-horizontal" role="form" action="add.do" method="post">
		<div class="form-group">
			<label for="firstname" class="col-sm-2 control-label">活动名称：</label>
			<div class="col-sm-10">
				<input type="text" name="title" class="form-control" id="firstname"
					placeholder="请输入活动名称">
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">活动发起人：</label>
			<div class=" col-xs-10" id="lastname">
				<input type="text" name="author" class="form-control"
					placeholder="请输入活动发起人"><br />
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">活动发起人手机号：</label>
			<div class=" col-xs-10" id="lastname">
				<input type="text" name="phone" class="form-control"
					placeholder="请输入活动发起人手机号"><br />
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">活动内容：</label>
			<div class=" col-xs-10" id="lastname">
				<textarea rows="10" cols="20" name="content" class="form-control"
					placeholder="请输入活动内容"></textarea>
				<br />
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">活动地点：</label>
			<div class=" col-xs-10" id="lastname">
				<input type="text" name="location" class="form-control"
					placeholder="请输入活动地点"><br />
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">活动开始时间：</label>
			<div class=" col-xs-10" id="lastname">
				<input type="text" name="begin_time" class="form-control"
					placeholder="请输入活动开始时间"><br />
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">活动结束时间：</label>
			<div class=" col-xs-10" id="lastname">
				<input type="text" name="end_time" class="form-control"
					placeholder="请输入活动结束时间"><br />
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">活动资金：</label>
			<div class=" col-xs-10" id="lastname">
				<input type="text" name="money" class="form-control"
					placeholder="请输入活动资金"><br />
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">活动最高人数：</label>
			<div class=" col-xs-10" id="lastname">
				<input type="text" name="peopleNum" class="form-control"
					placeholder="请输入活动最高人数"><br />
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-lg btn-block">添加</button>
				<button type="reset" class="btn btn-primary btn-lg btn-block">重置</button>
			</div>
		</div>
	</form>
</body>
</html>