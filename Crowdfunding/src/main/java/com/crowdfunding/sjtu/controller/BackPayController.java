package com.crowdfunding.sjtu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BackPayController {
// 	https://180.171.41.183/Crowdfunding/backpay 回调地址，要处理这个地址。
	@RequestMapping("/backpay")
	public String backPay(){
		return null;
	}
}
