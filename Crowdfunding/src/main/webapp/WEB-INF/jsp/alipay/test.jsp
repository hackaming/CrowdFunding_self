<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>付款</title>
</head>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.alipay.config.*"%>
<%@ page import="com.alipay.api.*"%>
<%@ page import="com.alipay.api.request.*"%>
<%
	//获得初始化的AlipayClient
	AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
	
	//设置请求参数
	AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
	alipayRequest.setReturnUrl(AlipayConfig.return_url);
	alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
	
	System.out.println("in test, now start to set the parameters");
	System.out.println("The WIDOUT get(via getparameter) is:"+request.getParameter("WIDout_trade_no"));
	//商户订单号，商户网站订单系统中唯一订单号，必填
	//String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
	String str =request.getAttribute("WIDout_trade_no").toString();
	String out_trade_no1 = new String(str.getBytes("ISO-8859-1"),"UTF-8");
	String str2 =request.getAttribute("WIDtotal_amount").toString();
	
	System.out.println("get str2 from getattribute is:"+str2);
	
	String strt = new String(str2.getBytes("ISO-8859-1"),"UTF-8");
	System.out.println("str2 has been called gtbytes:" + strt);
	
	String out_trade_no2 = new String(str.getBytes("ISO-8859-1"),"UTF-8");
	
	String str3 = request.getParameter("WIDtotal_amount");
	System.out.println("get parameter from str3 is:" + str3); 
	System.out.println(out_trade_no1);
	System.out.println(out_trade_no2);
	System.out.println();
	//付款金额，必填

%>
<body>
</body>
</html>