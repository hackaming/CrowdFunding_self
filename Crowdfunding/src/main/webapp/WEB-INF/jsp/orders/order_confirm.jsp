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
<form name="pay" action="${ctx }/order/confirmed" method="post">
ÇëÈ·ÈÏÄúµÄ¶©µ¥£º
¶©µ¥ºÅ:${order.orderId }
ÏîÄ¿id:${order.projectId }
ÄúÐèÒªÖ§¸¶:${order.totalAmount }
·ÝÊý£º${order.shares }
<input type="submit" value="È·ÈÏÖ§¸¶" />
</form>
</body>
</html>