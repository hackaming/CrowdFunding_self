<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String ctx = request.getContextPath();
    pageContext.setAttribute("ctx", ctx);
%>
<head>
<link rel="shortcut icon" href="${ctx}/img/LOGO-S.jpg" type="image/x-icon" />
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<meta name="description" content="">
	<meta name="keywords" content="">
	<script type="text/javascript">
	   var contextPath = "${ctx}";
	</script>
	<link href="${ctx}/css/bootstrap.min.css" rel="stylesheet" media="screen">
	<link rel="stylesheet" href="${ctx}/css/reset.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/css/common.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/css/new.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/css/top.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/css/concept.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/css/product.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/css/products.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/css/login.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/css/reg.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/css/transfer.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/css/company.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/css/style.css" type="text/css">
	<link rel="stylesheet" href="${ctx}/css/countdown.css" type="text/css">
	<link rel="shortcut icon" href="${ctx}/img/favicon.png">
	<title>中筹网金</title>
	<meta content="authenticity_token" name="csrf-param" />
	<meta content="IYIT8i0l3BA2+cw288v4vXkddSaKe77l2YKlRfwdp5Y=" name="csrf-token" />
	<script language="javascript" src="${ctx}/js/jquery-1.10.2.min.js" type="text/javascript"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/jquery.countdown.js"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/jquery.countdown-ja.js"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/counter.js"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/detail-calc.js"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/xxk.js"></script>
	<script language="javascript" type="text/javascript" src="${ctx}/js/jquery.lwtCountdown-1.0.js"></script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
	<script src="${ctx}/js/flowplayer-3.2.13.min.js"></script>
	<!-- start header -->
	<script type="text/javascript">
	$(document).ready(function() {
		<c:forEach items="${projectVoList}" var="projectVo" varStatus="p">
			var cn = '${projectVo.crowNumberPlain}';// 众筹份数
			var rcn = '${projectVo.crowRealNumberPlain}';// 众筹实际份数
			var percentage = Math.floor((rcn / cn) * 100) + "%";
			$("#bar_${p.index}").css('width', percentage);
			$("#percentage_${p.index}").html(percentage);
		</c:forEach>
	});
	</script>
</head>