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
<title>项目详细描述</title>
</head>
<body>
//从 modelmap的变量，projectlist里面逐个的把数据读出来。
<form>
<c:forEach items="${projectlist }" var="project" varStatus="p">
<form name="startcrowdfunding" action="${ctx }/orderstart?projectid=${project.projectId}" method="GET">
<a href="${ctx}/project/projectdetail/${project.projectId }">项目名称:"${project.projectName }"</a><br>
项目ID:"${project.projectId }" <br>
项目创建日期："${project.createDateTime }" <br>
众筹截止日期:"${project.deadDate }" <br>
图片名称:"${project.imageName }" <br>
项目地址:"${project.location }" <br>
项目价格:"${project.price }" <br>
项目状态:"${project.status }" <br>
<input type = "submit" value="参与众筹" />
</form>
<br>
<br>
</c:forEach>
</form>
</body>
</html>