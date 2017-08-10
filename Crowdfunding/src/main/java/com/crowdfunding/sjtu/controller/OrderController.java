package com.crowdfunding.sjtu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crowdfunding.sjtu.model.Orders;
import com.crowdfunding.sjtu.model.Project;
import com.crowdfunding.sjtu.model.User;
import com.crowdfunding.sjtu.service.IOrderService;
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
	@Autowired
	private IOrderService orderservice;
	Logger logger = Logger.getLogger(this.getClass());
	@RequestMapping(value="/orderstart")
	public String orderStart(String projectid,HttpServletRequest req,ModelMap model){
//projectID取不到值还要调试，查一下
		//String projectid=req.getParameter("projectid");
		//System.out.println("now project id get in orderconfirm from ordercontroller is:" + projectid);
		//show the project detail, ask the user to type in the number of shares want to buy and other information if there is
		//then submit???need to confirm
		System.out.println("The string projectid get is:"+projectid);
		System.out.println("The string projectid after convert to integer is:" + Integer.parseInt(projectid));
		Project project = projectservice.getProjectById(Integer.parseInt(projectid.trim()));
		model.addAttribute("project", project);
		return  "orders/order_submit";
	}
	
	@RequestMapping("/order/ordersubmit")
	public String orderSubmit(@RequestParam String projectId,@RequestParam String shares,HttpSession session,ModelMap model){
		//把双数接收过来
		//插入ORDER DB
		//重新读出来，显示出来
		//用户有确认，就跳转支付
		// 写完order表的DAO,SERVICE，之后再把这些重新读出来，显示给用户
		User user = (User)session.getAttribute("user");
		if (null != user){ //session里面没有值，则未登录成功，重新登录
			System.out.println("user id is"+user.getUserId());
			System.out.println("user name is"+user.getUserName() + "successfully get the user from session.");	
		} else{
			return "user/login";
		}
		Project project = projectservice.getProjectById(Integer.parseInt(projectId));
		Orders order = new Orders();
		order.setProjectId(project.getProjectId());
		order.setUserId(user.getUserId());
		order.setShares(Integer.parseInt(shares));
		float totalAmount = Float.parseFloat(project.getPrice()) * Float.parseFloat(shares);
		order.setTotalAmount(totalAmount);
		order.setStatus(0); //0 is the initial status, not confirm by user
		order.setComment("0 is theinitial status, not confirmed by user");
		int id = (Integer) orderservice.saveOrder(order);
		//如何将数据从DB里读出来？刚刚那条记录，并显示出来给用户确认，是一个问题。ORDER ID?
		//Orders confirmOrder = orderservice.getOrderById(orderId) ;
		System.out.println("The orderId is from (id):"+id);
		System.out.println("The orderId is:"+order.getOrderId());
		model.addAttribute("order", order);
		return "orders/order_confirm";
	}
	
	//用户确认订单正确，并且要跳转支付的时候，需要更新一下ORDER里面的这条记录的状态为，确认，并跳转支付。
	//更新状态的逻辑在这里写吧，更新完之后，应该是转到PAY里面
	@RequestMapping("/order/alreadyconfirmed")
	public String orderConfirmed(Orders order){
		//System.out.println("Test to see if the order can be get in this way:"+order.getOrderId());
		order.setStatus(1);
		order.setComment("now the user confirmed he/she needs to pay,time is:" + dateservice.getFullDate());
		System.out.println("begin to save into db with the new status");
		orderservice.saveOrUpdate(order); //update the status, then go to pay...
		logger.info("now the user confirmed he/she needs to pay,time is:" + dateservice.getFullDate()+"order id is:" + order.getOrderId());
		
		return "forward:/pay/orderpay";
	}
}
