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
<!-- ��ʼȷ�϶���֮��󣬸��¶���״̬Ϊ��ȷ�ϣ�Ȼ��ɶ����ת֧���ˣ����ڵ�֧���� -->
<form name="pay" action="${ctx }/order/confirmed" method="post">
��ȷ�����Ķ�����
������:${order.orderId }
��Ŀid:${order.projectId }
����Ҫ֧��:${order.totalAmount }
������${order.shares }
<input type="submit" value="ȷ��֧��" />
</form>
</body>
</html>