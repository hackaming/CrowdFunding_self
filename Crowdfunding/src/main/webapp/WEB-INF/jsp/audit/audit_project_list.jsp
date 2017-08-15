<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%>
<%
String ctx = request.getRequestURI();
pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${audits }" var="audits" varStatus="p">
<% //这个地方需要考虑一下，如何提交表单，不能只是这样显示出来吧？ %>
<a href="${ctx}/project/projectdetail/${audits.auditId}">审核编号："${audits.auditId}"</a>
提交用户："${audits.userId }"
项目ID: "${audits.projectId }"
创建时间："${audits.createDateTime }"
备注："${audits.comment }"
审核状态："${audits.status}"

</c:forEach>



</body>
</html>