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
	
	
// 	https://180.171.41.183/Crowdfunding/backpay 。
	@RequestMapping("/backpay/notify")
	public void backPay(HttpServletRequest request,HttpServletResponse resp) throws IOException, AlipayApiException{
	
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
		
				valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}
			
			boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type); //调用SDK验证签名

	
			if(signVerified) {
			
				String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
				String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");
			
			
				String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");
		
				
				JournalTractional journal = new JournalTractional();
				journal.setDateTime(dateservice.getFullDate());
				journal.setStatus(1); //get from to alipay
				journal.setOut_trade_no(out_trade_no);
				journal.setTrade_no(trade_no);
				journal.setTrade_status(trade_status);
				ijournalTractionalService.save(journal);   
				
				if(trade_status.equals("TRADE_FINISHED")){
			
				}else if (trade_status.equals("TRADE_SUCCESS")){
		
				}
	
				resp.getWriter().println("success");
		
				
			}else {
				resp.getWriter().println("fail");
			
		
			}
			
		
	}
	@RequestMapping(value="/alipay/return")
	public String returnUrl(String total_amount,String timestamp,String sign,String trade_no,String sign_type,String auth_app_id,String charset,String seller_id,String method,String app_id,String out_trade_no,String version){
		System.out.println("return_url is called(page)");
	return "forward:/alipay/return_url";	
	}
}
