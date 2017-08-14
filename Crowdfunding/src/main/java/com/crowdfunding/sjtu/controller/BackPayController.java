package com.crowdfunding.sjtu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alipay.api.AlipayApiException;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.config.AlipayConfig;
import com.crowdfunding.sjtu.model.JournalTractional;
import com.crowdfunding.sjtu.service.IJournalTractionalService;
import com.crowdfunding.sjtu.utility.IDateService;

@Controller
public class BackPayController {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private IJournalTractionalService ijournalTractionalService;
	
	@Autowired 
	private IDateService dateservice;
	
	
// 	https://180.171.41.183/Crowdfunding/backpay 回调地址，要处理这个地址。
	@RequestMapping("/backpay/notify")
	public void backPay(HttpServletRequest request,HttpServletResponse resp) throws IOException, AlipayApiException{
		/* *
		 * 功能：支付宝服务器异步通知页面
		 * 日期：2017-03-30
		 * 说明：
		 * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
		 * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。


		 *************************页面功能说明*************************
		 * 创建该页面文件时，请留心该页面文件中无任何HTML代码及空格。
		 * 该页面不能在本机电脑测试，请到服务器上做测试。请确保外部可以访问该页面。
		 * 如果没有收到该页面返回的 success 
		 * 建议该页面只做支付成功的业务逻辑处理，退款的处理请以调用退款查询接口的结果为准。
		 */
		 
			//获取支付宝POST过来反馈信息
			Map<String,String> params = new HashMap<String,String>();
			Map<String,String[]> requestParams = request.getParameterMap();
			System.out.println("BackPayController is called!");
			for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

			//——请在这里编写您的程序（以下代码仅作参考）——
			
			/* 实际验证过程建议商户务必添加以下校验：
			1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
			2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
			3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
			4、验证app_id是否为该商户本身。
			*/
		
			if(signVerified) {//验证成功
				//商户订单号
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
				//支付宝交易号
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
				//交易状态
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
				//这里还要改。。。
				
				JournalTractional journal = new JournalTractional();
				journal.setDateTime(dateservice.getFullDate());
				journal.setStatus(1); //get from to alipay
				journal.setOut_trade_no(out_trade_no);
				journal.setTrade_no(trade_no);
				journal.setTrade_status(trade_status);
				ijournalTractionalService.save(journal);    //保存进db
				
				if(trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				}else if (trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
					//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
					//如果有做过处理，不执行商户的业务程序
					
					//注意：
					//付款完成后，支付宝系统发送该交易状态通知
				}
				//给支付宝通知
				resp.getWriter().println("success");
				//要加上自己的业务逻辑，更新数据之类的什么东西
				
			}else {//验证失败
				resp.getWriter().println("fail");
			
				//调试用，写文本函数记录程序运行情况是否正常
				//String sWord = AlipaySignature.getSignCheckContentV1(params);
				//AlipayConfig.logResult(sWord);
			}
			
			//——请在这里编写您的程序（以上代码仅作参考）——
	}
	@RequestMapping(value="/alipay/return")
	public String returnUrl(String total_amount,String timestamp,String sign,String trade_no,String sign_type,String auth_app_id,String charset,String seller_id,String method,String app_id,String out_trade_no,String version){
		System.out.println("return_url is called(page)");
	return "forward:/alipay/return_url";	
	}
}
