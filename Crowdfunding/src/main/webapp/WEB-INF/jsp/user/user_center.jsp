<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<% 
	String ctx=request.getContextPath();
	pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- 1.用户中心要显示用户自己的各种状态，改密码，等
2.显示所持有的项目份额的状态
3.可以发布转让需求，发布好了之后，需要审批才能发布成功，并显示在转让中心
4.调用债券转让接口还是调用支付宝呢？？
session里面有user，加上更必密码等功能？要不要这个功能现在？
modelmap里面有orders
可以循环显示出来了，暂时先搞这个转让中心吧
5.应该分成几个页面或者TAB的形式吧，用户资料就是用户资料，订单TAB就是订单TAB -->
<form name="userinfo" action="test" method="post">
用户id:"${user.userId }"
用户名："${user.userName }"
<input type="submit" value="更改密码" />
</form>
<c:forEach items="orders" var="order" varStatus="p" >
<!-- 这里要加一个判断，是否支付成功，支付成功才转让，否则就支付 -->
<c:if test="${order.status == 1}">
<form name="order" action="${ctx/transfer }" method="post">
订单号<input name="orderId" value="${order.orderId }" /><br>
创建日期<input name="createDateTime" value="${order.createDateTime }" /><br>
项目号<input name="projectId" value="${order.projectId }" /><br>
所属用户ID<input name="userId" value="${order.userId }" /><br>
金额<input name="totalAmount" value="${order.totalAmount }" /><br>
状态（已支付/未支付/转让中）<input name="status" value="${order.status }" /><br>
份额数量<input name="shares" value="${order.shares }" /><br>
备注<input name="comment" value="${order.comment}" /><br>
<input type="submit" name="action" value="转让" />
</form>
</c:if>
</c:forEach>

<c:forEach items="orders" var="order" varStatus="p" >
这里要加一个判断，是否支付成功，支付成功才转让，否则就支付
<c:if test="${order.status == 0}">
<form name="order" action="${ctx}/order/alreadyconfirmed" method="post">     <-- 跳转支付页面开始支付，要考虑参数什么的 -->
订单号<input name="orderId" value="${order.orderId }" /><br>
创建日期<input name="createDateTime" value="${order.createDateTime }" /><br>
项目号<input name="projectId" value="${order.projectId }" /><br>
所属用户ID<input name="userId" value="${order.userId }" /><br>
金额<input name="totalAmount" value="${order.totalAmount }" /><br>
状态（已支付/未支付/转让中）<input name="status" value="${order.status }" /><br>
份额数量<input name="shares" value="${order.shares }" /><br>
备注<input name="comment" value="${order.comment}" /><br>

<input type="hidden" name="WIDsubject" value="${order.projectId }"/><br>
<input type="hidden" name="WIDout_trade_no" value="${order.orderId }"/><br>
<input type="hidden" name="WIDtotal_amount" value="${order.totalAmount }"/><br>
<input type="hidden" name="WIDbody" value="${order.shares }"/><br>
<input type="hidden" name="status" value="${order.status }"/><br>
<input type="hidden" name="comment" value="${order.comment }"/><br>
<input type="hidden" name="createDateTime" value="${order.createDateTime }"/><br>
<input type="submit" name="action" value="支付" />	
</form>
</c:if>
</c:forEach>
</body>
</html>