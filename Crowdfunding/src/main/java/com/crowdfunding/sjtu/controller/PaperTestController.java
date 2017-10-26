package com.crowdfunding.sjtu.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;

import com.crowdfunding.sjtu.MQ.OrderProduce;
import com.crowdfunding.sjtu.service.IOrderService;
import com.crowdfunding.sjtu.service.IProjectService;
import com.crowdfunding.sjtu.service.IRequestSerialService;
import com.crowdfunding.sjtu.service.IUserService;
import com.crowdfunding.sjtu.utility.IDateService;

@Controller
public class PaperTestController {
	@Autowired
	private OrderProduce orderproduce;
	@Autowired
	private IProjectService projectservice;
	@Autowired
	private IUserService userservice;
	@Autowired
	private IDateService dateservice;
	@Autowired
	private IOrderService orderservice;
	@Autowired
	private IRequestSerialService requestserialservice;

	Logger logger = Logger.getLogger(this.getClass());
	

}
