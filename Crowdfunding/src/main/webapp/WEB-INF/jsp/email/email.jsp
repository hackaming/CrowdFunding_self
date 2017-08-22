<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%
String ctx=request.getContextPath();
pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>e-mail sending</title>
</head>
<body>
<form name="emailtest" action="${ctx }/mailsend" method="post">
发送邮件：
<input type="submit" name="emailsending" value="发送">
</form>
</body>
</html>