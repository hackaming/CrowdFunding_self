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
<!-- ¿ªÊ¼È·ÈÏ¶©µ¥Ö®ºóºó£¬¸üÐÂ¶©µ¥×´Ì¬ÎªÒÑÈ·ÈÏ£¬È»ºóÉ¶£¿Ìø×ªÖ§¸¶ÁË£¬ÖÕÓÚµ½Ö§¸¶ÁË -->
<form name="pay" action=""${ctx }"/order/confirmed" method="post">
ÇëÈ·ÈÏÄúµÄ¶©µ¥£º<br>
¶©µ¥ºÅ:"${order.orderId }"<br>
ÏîÄ¿id:"${order.projectId }"<br>
ÄúÐèÒªÖ§¸¶:"${order.totalAmount }"<br>
·ÝÊý£º"${order.shares }"<br>

<input type="hidden" name="projectId" value="${project.projectId }"/><br>
<input type="hidden" name="orderId" value="${project.orderId }"/><br>
<input type="hidden" name="totalAmount" value="${project.totalAmount }"/><br>
<input type="hidden" name="shares" value="${project.shares }"/><br>
<input type="hidden" name="status" value="${project.status }"/><br>
<input type="hidden" name="comment" value="${project.comment }"/><br>
<input type="hidden" name="createDateTime" value="${project.createDateTime }"/><br>

<input type="submit" value="È·ÈÏÖ§¸¶" />
</form>
</body>
</html>