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
<title>alreadyconfirmed</title>
</head>
<body>
<!-- ¿ªÊ¼È·ÈÏ¶©µ¥Ö®ºóºó£¬¸üÐÂ¶©µ¥×´Ì¬ÎªÒÑÈ·ÈÏ£¬È»ºóÉ¶£¿Ìø×ªÖ§¸¶ÁË£¬ÖÕÓÚµ½Ö§¸¶ÁË -->
<form name="payalreadyconfirmed" action="${ctx}/order/alreadyconfirmed" method="post">
ÇëÈ·ÈÏÄúµÄ¶©µ¥£º<br>
¶©µ¥ºÅ:"${order.orderId }"<br>
ÏîÄ¿id:"${order.projectId }"<br>
ÄúÐèÒªÖ§¸¶:"${order.totalAmount }"<br>
·ÝÊý£º"${order.shares }"<br>

<input type="hidden" name="WIDsubject" value="Project${order.projectId }"/><br>
<input type="hidden" name="WIDout_trade_no" value="${order.orderId }"/><br>
<input type="hidden" name="WIDtotal_amount" value="${order.totalAmount }"/><br>
<input type="hidden" name="WIDbody" value="Shares${order.shares }"/><br>
<input type="hidden" name="status" value="${order.status }"/><br>
<input type="hidden" name="comment" value="${order.comment }"/><br>
<input type="hidden" name="createDateTime" value="${order.createDateTime }"/><br>

<input type="submit" value="È·ÈÏÖ§¸¶" />
</form>
</body>
</html>