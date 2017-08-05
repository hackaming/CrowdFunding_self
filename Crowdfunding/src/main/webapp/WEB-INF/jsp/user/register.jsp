<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="UTF-8"%>
    
<%-- <%   
String path = request.getContextPath();   
System.out.println("request.getContextPath():"+path);
System.out.println("request.getScheme():"+request.getScheme());
System.out.println("request.getServerName():"+request.getServerName());
System.out.println("request.getServerPort():"+request.getServerPort());
String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;   
%>   
<base href="<%=basePath%>" >  --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>注册</title>
</head>
<body>

<form name="registration" action="crowdfunding/reg" method="post">
用户名：<input type="text" name = "userName" /><br>
密码：<input type="text" name = "password" /><br>
确认密码：<input type="text" name = "password1" /><br>
性别：<input type="text" name = "sex" /><br>
手机号<input type="text" name = "cellPhone" /><br>
<input type="submit"  value="注册" />
</form>
</body>
</html>