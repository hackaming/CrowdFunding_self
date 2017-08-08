package com.crowdfunding.sjtu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crowdfunding.sjtu.model.Project;
import com.crowdfunding.sjtu.model.User;
import com.crowdfunding.sjtu.service.IProjectService;
import com.crowdfunding.sjtu.service.IUserService;
import com.crowdfunding.sjtu.utility.IDateService;

@Controller
public class OrderController {
	@Autowired
	private IProjectService projectservice;
	@Autowired
	private IUserService userservice;
	@Autowired
	private IDateService dateservice;
	
	@RequestMapping(value="/orderstart")
	public String orderStart(HttpServletRequest req,ModelMap model){
		String projectid=req.getParameter("projectid");
		System.out.println("now project id get in orderconfirm from ordercontroller is:" + projectid);
		//show the project detail, ask the user to type in the number of shares want to buy and other information if there is
		//then submit???need to confirm
		Project project = projectservice.getProjectById(Integer.parseInt(projectid));
		model.addAttribute("project", project);
		return  "order/order_submit";
	}
	@RequestMapping("/order/ordersubmit")
	public String orderSubmit(@RequestParam String projectId,@RequestParam String shares,HttpSession session,ModelMap model){
		//把双数接收过来
		//插入ORDER DB
		//重新读出来，显示出来
		//用户有确认，就跳转支付
		/* 写完order表的DAO,SERVICE，之后再把这些重新读出来，显示给用户
		User user = (User)session.getAttribute("user");
		Project project = projectservice.getProjectById(Integer.parseInt(projectId));
		model.addAttribute("user", user);
		model.addAttribute("project", project);
		model.addAttribute("shares", shares);
		*/
		return "order/order_submit";
	}
}
