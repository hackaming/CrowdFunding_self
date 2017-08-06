<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%   
String path = request.getContextPath();   
System.out.println("request.getContextPath():"+path);
System.out.println("request.getScheme():"+request.getScheme());
System.out.println("request.getServerName():"+request.getServerName());
System.out.println("request.getServerPort():"+request.getServerPort());
String basePath = request.getScheme()+"://" +request.getServerName()+":" +request.getServerPort()+path+"/" ;   
System.out.println("the basePath is:"+basePath);
%>   
<base href="<%=basePath%>" > 

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>登录成功</title>
</head>
<body>
登录成功
<a href="/projectList">登录成功查看项目列表</a>
</body>
</html>