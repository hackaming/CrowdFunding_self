<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"
%>
<%
String ctx = request.getRequestURI();
pageContext.setAttribute("ctx", ctx);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>Insert title here</title>
</head>
<body>
<c:forEach items="${audits }" var="audits" varStatus="p">
<% //����ط���Ҫ����һ�£�����ύ��������ֻ��������ʾ�����ɣ� %>
<a href="${ctx}/project/projectdetail/${audits.auditId}">��˱�ţ�"${audits.auditId}"</a>
�ύ�û���"${audits.userId }"
��ĿID: "${audits.projectId }"
����ʱ�䣺"${audits.createDateTime }"
��ע��"${audits.comment }"
���״̬��"${audits.status}"

</c:forEach>



</body>
</html>