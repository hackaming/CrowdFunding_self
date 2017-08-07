<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>项目详请列表</title>
</head>
<body>
项目ID:"${project.projectId }" <br>
项目创建日期："${project.createDateTime }" <br>
众筹截止日期:"${project.deadDate }" <br>
图片名称:"${project.imageName }" <br>
项目地址:"${project.location }" <br>
项目价格:"${project.price }" <br>
项目状态:"${project.status }" <br>
</body>
</html>