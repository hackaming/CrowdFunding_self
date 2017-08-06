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
<title>login failuree</title>
</head>
<body>
登录失败
<a href="login">重新登</a>>

</body>
</html>