<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function check(){
	path = document.getElementById("file").value;
	document.getElementById("name").value = path;
	
}
	

</script>
</head>
<body>
<form action="upload.do" enctype="multipart/form-data" method="post">
	<input type="file" name="file" id="file">
	<input type="text" name="name" id="name"/>
	<input type="submit" value="上传" onclick="check()"/>

</form>


</body>
</html>