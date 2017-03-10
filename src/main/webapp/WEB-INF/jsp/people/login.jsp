<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<title>登陆界面</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css">
<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</head>
<body>
	<div>
		<span style="color: red;">${message }</span>
	</div>

	<form class="form-horizontal" role="form" action="login.do">
		<div class="form-group">
			<label for="firstname" class="col-sm-2 control-label">手机号：</label>
			<div class="col-sm-10">
				<input type="text" name="phone" class="form-control" id="firstname"
					placeholder="请输入手机号">
			</div>
		</div>
		<div class="form-group">
			<label for="lastname" class="col-sm-2 control-label">验证码：</label>
			<div class=" col-xs-10" id="lastname">
				<input type="text" name="idCode" class="form-control"
					placeholder="请输入验证码"><br />

				<button type="button" id="send"
					class="btn btn-primary btn-lg btn-block">发送验证码</button>

			</div>

		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn btn-primary btn-lg btn-block">登录</button>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	send = document.getElementById('send');
	send.onclick = function() {
		var that = this;
		var times = 60;
		this.disabled = true;

		timer1 = setInterval(function() {
			times--;
			that.value = times + "秒后重试";
			if (times <= 0) {
				that.disabled = false;
				that.value = "发送验证码";
				clearInterval(timer1);
				times = 60;
			}
		}, 1000);
		$.ajax({
			url : "sendIdCode.do",
			type : "post",

			success : function() {
				return true;
			},

			error : function() {
				return false;
			},

			timeout : 1000 * 60,
		});
	}
</script>
</html>