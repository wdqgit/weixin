<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath }/css/bootstrap.min.css">  
	<script src="${pageContext.request.contextPath }/js/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>

</head>
<body>
<form action="sendVideo.do" method="post" enctype="multipart/form-data" class="form-horizontal" role="form">
	
	
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">标题：</label>
		<div class="col-sm-10">
			<input type="text" name="title" class="form-control" id="firstname" 
				   placeholder="请输入标题"/>
		</div>
	</div>
	<div class="form-group">
		<label for="firstname" class="col-sm-2 control-label">描述：</label>
		<div class="col-sm-10">
			<input type="text" name="description" class="form-control" id="firstname" 
				   placeholder="请输入描述">
		</div>
	</div>
	
	<div class="form-group">
		<label for="lastname"  class="col-sm-2 control-label">视频：</label>
		<div class=" col-xs-10" id="lastname" >
			<input type="file" name="file" class="form-control" id="firstname" 
				   >
			
		</div>
		
	</div>
	<button type="submit" class="btn btn-primary">提交</button>

</form>


</body>
</html>