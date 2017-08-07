<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>创建项目</title>
</head>
<body>
	<form name="createProject" action="${ctx}/project/create" method="post">
		项目的名称：<input type ="text" name="projectName" /><br> 
		项目的描述： <input type="text" name="description" /><br> 
		项目的图片名称：<input type ="text" name="imagename" /> <br> 
		众筹截止日期：<input type ="text" name="deadDate" /> <br> 
		项目的单价:<input type = "text" name="price" /> <br> 
		项目的地址：<input type	= "text" name="location" /> <br> 
		备注：<input type= "text" name="comment" /><br> 
		<input type = "submit" value="创建" />
		<br>
	</form>
</body>
</html>