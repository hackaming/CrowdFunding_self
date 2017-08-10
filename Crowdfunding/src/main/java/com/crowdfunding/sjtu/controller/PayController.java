package com.crowdfunding.sjtu.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crowdfunding.sjtu.model.Orders;

@Controller
public class PayController {
	private Logger logger = Logger.getLogger(this.getClass());
	// 
	@RequestMapping("/pay/orderpay")
	public String orderPay(Orders order,ModelMap modelmap){
		logger.info("Now begin in /pay/orderpay action!");
		//这里，填入参数，把ORDER里面的东西接收过来填 到对应的参数里面，调下面的链接连支付宝
		modelmap.addAttribute("WIDout_trade_no", order.getOrderId()); //订单号
		modelmap.addAttribute("WIDtotal_amount", order.getTotalAmount()); //交易金额
		modelmap.addAttribute("WIDsubject", "projectId:" + order.getProjectId()); //订单名称
		modelmap.addAttribute("WIDbody", "dd"); //商品描述 可空
		return "alipay/test";
		//return "orders/order_confirm";
	}
}
