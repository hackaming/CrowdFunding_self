package com.crowdfunding.sjtu.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.config.AlipayConfig;
import com.crowdfunding.sjtu.model.JournalTractional;
import com.crowdfunding.sjtu.model.Orders;
import com.crowdfunding.sjtu.service.IJournalTractionalService;
import com.crowdfunding.sjtu.utility.IDateService;

@Controller
public class PayController {
	private Logger logger = Logger.getLogger(this.getClass());
	@Autowired
	private IJournalTractionalService ijournalTractionalService;
	
	@Autowired 
	private IDateService dateservice;
	
	// 
	@RequestMapping("/pay/orderpay1")
	public String orderPay1(Orders order,RedirectAttributes  modelmap){
		logger.info("Now begin in /pay/orderpay action!");
		//这里，填入参数，把ORDER里面的东西接收过来填 到对应的参数里面，调下面的链接连支付宝
		modelmap.addFlashAttribute("WIDout_trade_no", order.getOrderId()); //订单号
		modelmap.addFlashAttribute("WIDtotal_amount", order.getTotalAmount()); //交易金额
		modelmap.addFlashAttribute("WIDsubject", "projectId:" + order.getProjectId()); //订单名称
		modelmap.addFlashAttribute("WIDbody", "dd"); //商品描述 可空
		
		System.out.println("(Will transfer to next page?)Total amount in orderpay action is:"+order.getTotalAmount());				

		return "alipay/start_pay";
		//return "orders/order_confirm";
	}
	
	@RequestMapping("/pay/orderpay")
	public void orderPay(HttpServletRequest request,HttpServletResponse resp) throws IOException, AlipayApiException{
		//获得初始化的AlipayClient
		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
		
		//设置请求参数
		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
		alipayRequest.setReturnUrl(AlipayConfig.return_url);
		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
		
		System.out.println("in start_pay.jsp, now start to set the parameters");
		//商户订单号，商户网站订单系统中唯一订单号，必填
	 	/*String str2 =request.getAttribute("WIDout_trade_no").toString();
		String out_trade_no = new String(str2.getBytes("ISO-8859-1"),"UTF-8");*/ 
		
		//接着改order_confirm里面的参数？？要把这几个参数BUILD好后发出去要不就改本参数。
		String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
		
		
		
		//付款金额，必填
		String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
		
		/* str2 =request.getAttribute("WIDtotal_amount").toString();
		String total_amount = new String(str2.getBytes("ISO-8859-1"),"UTF-8");*/ 
		
		//订单名称，必填
		String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
		/* str2 =request.getAttribute("WIDsubject").toString();
		String subject = new String(str2.getBytes("ISO-8859-1"),"UTF-8");*/ 
		
		
		//商品描述，可空
		String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");
		 
		/*str2 =request.getAttribute("WIDbody").toString();
		String body = new String(str2.getBytes("ISO-8859-1"),"UTF-8"); */
		System.out.println(out_trade_no + total_amount + subject +body);
		System.out.println("Now begin to call the setbizcontent");
		alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
				+ "\"total_amount\":\""+ total_amount +"\"," 
				+ "\"subject\":\""+ subject +"\"," 
				+ "\"body\":\""+ body +"\"," 
				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		
		//若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
		//alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," 
		//		+ "\"total_amount\":\""+ total_amount +"\"," 
		//		+ "\"subject\":\""+ subject +"\"," 
		//		+ "\"body\":\""+ body +"\"," 
		//		+ "\"timeout_express\":\"10m\"," 
		//		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
		//请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节
		
		System.out.println("Now begin to call the alipayclient to send the request.");
		//请求
		//交易流水：
		JournalTractional journal = new JournalTractional();
		journal.setDateTime(dateservice.getFullDate());
		journal.setStatus(0); //send to alipay
		journal.setwIDout_trade_no(out_trade_no);
		journal.setwIDsubject(subject);
		journal.setwIDtotal_amount(total_amount);
		journal.setwIDbody(body);
		ijournalTractionalService.save(journal); //request saved into db, then send to alipay
		
		String result = alipayClient.pageExecute(alipayRequest).getBody();
		
		System.out.println("Now print out the result to the user");
		//输出
		//还需要给到交易流水吧？？？？
/*		PrintWriter out = resp.getWriter();
		out.println(result);
		resp.*/
		resp.setContentType("text/html;charset=UTF-8");
		resp.getWriter().print(result);
		//resp.getWriter().p
		//out.println(result);
	}
	
}

