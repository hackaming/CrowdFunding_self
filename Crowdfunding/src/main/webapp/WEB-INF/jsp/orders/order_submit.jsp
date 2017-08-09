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
<title>提交订单</title>
</head>
<body>
项目ID:"${project.projectId }" <br>
项目创建日期："${project.createDateTime }" <br>
众筹截止日期:"${project.deadDate }" <br>
图片名称:"${project.imageName }" <br>
项目地址:"${project.location }" <br>
项目价格:"${project.price }" <br>
项目状态:"${project.status }" <br>
<form name="ordersubmit" action="${ctx }/order/ordersubmit" method="post">
输入众筹份数<input type="input" name="shares" /><br>
 <input type="hidden" name="projectId" value="${project.projectId }"/><br>
<input type="submit" value="确认众筹" />
</form>
</body>
</html>