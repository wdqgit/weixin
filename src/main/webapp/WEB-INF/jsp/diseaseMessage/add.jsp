<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<form action="add.do" class="form-horizontal" role="form" method="post">
<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">病症名称：</label>
		<div class="col-sm-10">
			<input type="text" name="title" class="form-control" id="firstname" 
				   placeholder="请输入病症名称">
		</div>
	</div>
	<div class="form-group">
		<label for="lastname"  class="col-sm-2 control-label">病症描述：</label>
		<div class=" col-xs-10" id="lastname" >
			
			<textarea name="content" class="form-control" rows="3" placeholder="请输入病症描述"></textarea>
			<br/>
		</div>
		
	</div>
		<div class="form-group">
		<label for="lastname"  class="col-sm-2 control-label">解决建议：</label>
		<div class=" col-xs-10" id="lastname" >
			<textarea name="solution" class="form-control" rows="3" placeholder="请输入解决建议"></textarea>
		 
			
		</div>
		
	</div>
	
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-primary">提交</button>
			<a href="javascript:window.history.back(-1);" class="btn btn-primary" role="button">返回</a>
		</div>
	</div>

</form>
</body>
</html>