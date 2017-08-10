<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- 开始确认订单之后后，更新订单状态为已确认，然后啥？跳转支付了，终于到支付了 -->
<form name="pay" action=""${ctx }"/order/confirmed" method="post">
请确认您的订单：<br>
订单号:"${order.orderId }"<br>
项目id:"${order.projectId }"<br>
您需要支付:"${order.totalAmount }"<br>
份数："${order.shares }"<br>

<input type="hidden" name="projectId" value="${project.projectId }"/><br>
<input type="hidden" name="orderId" value="${project.orderId }"/><br>
<input type="hidden" name="totalAmount" value="${project.totalAmount }"/><br>
<input type="hidden" name="shares" value="${project.shares }"/><br>
<input type="hidden" name="status" value="${project.status }"/><br>
<input type="hidden" name="comment" value="${project.comment }"/><br>
<input type="hidden" name="createDateTime" value="${project.createDateTime }"/><br>

<input type="submit" value="确认支付" />
</form>
</body>
</html>