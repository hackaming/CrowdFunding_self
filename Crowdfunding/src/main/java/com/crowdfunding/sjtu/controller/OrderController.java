package com.crowdfunding.sjtu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crowdfunding.sjtu.MQ.OrderProduce;
import com.crowdfunding.sjtu.Vo.RequestSerialVO;
import com.crowdfunding.sjtu.model.Orders;
import com.crowdfunding.sjtu.model.Project;
import com.crowdfunding.sjtu.model.User;
import com.crowdfunding.sjtu.service.IOrderService;
import com.crowdfunding.sjtu.service.IProjectService;
import com.crowdfunding.sjtu.service.IRequestSerialService;
import com.crowdfunding.sjtu.service.IUserService;
import com.crowdfunding.sjtu.utility.IDateService;

@Controller
public class OrderController {
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
	@Autowired
	RedisTemplate<String, Object> redisTemplate;

	Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping(value = "/orderstart")
	public String orderStart(String projectid, HttpServletRequest req, ModelMap model, HttpSession session) {
		// projectID取不到值还要调试，查一下
		// String projectid=req.getParameter("projectid");
		// System.out.println("now project id get in orderconfirm from
		// ordercontroller is:" + projectid);
		// show the project detail, ask the user to type in the number of shares
		// want to buy and other information if there is
		// then submit???need to confirm
		System.out.println("The string projectid get is:" + projectid);
		System.out.println("The string projectid after convert to integer is:" + Integer.parseInt(projectid));
		Project project = projectservice.getProjectById(Integer.parseInt(projectid.trim()));
		model.addAttribute("project", project);
		// orderSubmitToMq(session); //测试代码，模拟用户下单，直接调用该参数看是否会发送给消息队列
		return "orders/order_submit";
	}

	@RequestMapping("/order/test")
	public String orderStringtest() {
		//这里需要写一段测试代码，获取某个用户的SESSION,批量产多少个数据，什么的。然后看会不会有效果！！
		//目前主要是要形成一个RequestVO，然后发给后台，进行处理，至于说处理请求，还要改SERVIE层！
		for (int i = 0; i < 1; i++) {
			orderproduce.sendDataToQueue("ordersCrowdfunding", "dfdfd");
		}
		return "user/login";
	}
	
	@RequestMapping("/order/orderTestMq")
	public String orderStringToMq(@RequestParam int projectId, @RequestParam String shares, HttpSession session,
			ModelMap model) {
		User user = (User) session.getAttribute("user");
		if (null != user) { // session里面没有值，则未登录成功，重新登录
			System.out.println("user id is" + user.getUserId());
			System.out.println("user name is" + user.getUserName() + "successfully get the user from session.");
		} else {
			return "user/login";
		}
		Project project = projectservice.getProjectById((projectId));
		RequestSerialVO requestserialvo = new RequestSerialVO();
		Orders order = new Orders();
		requestserialvo.setProjectid(projectId);
		requestserialvo.setUserid(user.getUserId());
		requestserialvo.setShares(Integer.parseInt(shares));
		requestserialvo.setPrice(Float.parseFloat(project.getPrice()));
		requestserialvo.setId(requestserialservice.getRequestSerial(user.getUserId() + ""));
		logger.info(
				"now the request serial's been generated and will send to the mq, then will be starting to check if the result shows"
						+ requestserialvo.getId());

		System.out.println("The requestserial vo's generated, the id is:" + requestserialvo.getId());
		orderproduce.sendDataToQueue("ordersCrowdfunding", requestserialvo);
		// 这里增加查询订单是否结束的代码！！！

		return null;
	}
//2017/10/14
	@RequestMapping("/order/ordersubmit")  // 原先这个链接不是叫这个名字，是下面这个，现在不用下面这个，改用MQ，2017/10/13
	public String orderSubmitToMq(@RequestParam int projectId, @RequestParam String shares, HttpSession session,
			ModelMap model) {
		User user = (User) session.getAttribute("user");
		if (null != user) { // session里面没有值，则未登录成功，重新登录
			System.out.println("user id is" + user.getUserId());
			System.out.println("user name is" + user.getUserName() + "successfully get the user from session.");
		} else {
			return "user/login";
		}
		Project project = projectservice.getProjectById(projectId);
		RequestSerialVO requestserialvo = new RequestSerialVO();
		requestserialvo.setProjectid(projectId);
		requestserialvo.setUserid(user.getUserId());
		requestserialvo.setShares(Integer.parseInt(shares));
		requestserialvo.setPrice(Float.parseFloat(project.getPrice()));
		requestserialvo.setId(requestserialservice.getRequestSerial(user.getUserId() + ""));
		logger.info(
				"now the request serial's been generated and will send to the mq, then will be starting to check if the result shows"
						+ requestserialvo.getId());

		System.out.println("The requestserial vo's generated, the id is:" + requestserialvo.getId()+"now send to MQ to deal with!");
		orderproduce.sendDataToQueue("ordersCrowdfunding", requestserialvo);
		
		//应该是根据这个请求流水，将数据读出来，跳转order_confirm
		System.out.println("need to add code here to check the result in Redis");
		Orders order = testRedisWithMapGet(requestserialvo.getId()); //get the order from redis		
		model.addAttribute("order", order);	
		return "orders/order_confirm";
	}
	
	public void tt(final String key){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				Orders order = testRedisWithMapGet(key);
			}
		}, 500, 10000);
	}
	
	public Orders testRedisWithMapGet(String key) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		HashMap<Object,Object> map =new HashMap();
		
		for (int i = 0;i<10;i++){
			if ((hash.entries(key).size()==0)){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		Orders o = null;
		if ((hash.entries(key).size()!=0)){
			map =  (HashMap<Object, Object>) hash.entries(key);
			System.out.println(map);
			o = new Orders();
			o.setComment((String) map.get("comment"));
			o.setShares((Integer) map.get("shares"));
			o.setStatus((Integer) map.get("shares"));
			o.setTotalAmount((Float) map.get("totalAmount"));
			o.setUserId((Integer) map.get("userId"));
			o.setProjectId((Integer) map.get("projectId"));
			o.setCreateDateTime((String) map.get("createDateTime"));
			o.setOrderId((Integer) map.get("orderId"));
		} else {
			System.out.println("Order get failed,it is null!,Need to think out a way to get the data!");
		}
		if (o != null){
			System.out.println("Order get from redis!--start");
			System.out.println(o.getComment());
			System.out.println(o.getCreateDateTime());
			System.out.println(o.getOrderId());
			System.out.println(o.getUserId());
			System.out.println(o.getStatus());
			System.out.println("Order get from redis!--end--now return to order controller");
		} else{
			System.out.println("Order get failed,it is null!");
		}

		return o;
	}

	@RequestMapping("/order/ordersubmit1") //原来是直接存DB，改成MQ的形式2017/10/13
	public String orderSubmit(@RequestParam String projectId, @RequestParam String shares, HttpSession session,
			ModelMap model) {
		// 把双数接收过来
		// 插入ORDER DB
		// 重新读出来，显示出来
		// 用户有确认，就跳转支付
		// 写完order表的DAO,SERVICE，之后再把这些重新读出来，显示给用户
		User user = (User) session.getAttribute("user");
		if (null != user) { // session里面没有值，则未登录成功，重新登录
			System.out.println("user id is" + user.getUserId());
			System.out.println("user name is" + user.getUserName() + "successfully get the user from session.");
		} else {
			return "user/login";
		}
		Project project = projectservice.getProjectById(Integer.parseInt(projectId));
		Orders order = new Orders();
		order.setProjectId(project.getProjectId());
		order.setUserId(user.getUserId());
		order.setShares(Integer.parseInt(shares));
		float totalAmount = Float.parseFloat(project.getPrice()) * Float.parseFloat(shares);
		order.setTotalAmount(totalAmount);
		order.setStatus(0); // 0 is the initial status, not confirm by user
		order.setComment("0 is theinitial status, not confirmed by user");
		orderservice.saveOrder(order);
		System.out.println("id is:" + order.getOrderId());

		// order = orderservice.saveorupdatecopy(order);

		// 如何将数据从DB里读出来？刚刚那条记录，并显示出来给用户确认，是一个问题。ORDER ID?
		// Orders confirmOrder = orderservice.getOrderById(orderId) ;
		// System.out.println("The orderId is from (id):"+id);
		System.out.println("The orderId is:" + order.getOrderId());
		model.addAttribute("order", order);
		
		
		//below section's only for testing!!!
		RequestSerialVO requestserialvo = new RequestSerialVO();

		Project project1 = projectservice.getProjectById(Integer.parseInt(projectId));
		Orders order1 = new Orders();
		requestserialvo.setProjectid(project1.getProjectId());
		requestserialvo.setUserid(user.getUserId());
		requestserialvo.setShares(Integer.parseInt(shares));
		requestserialvo.setPrice(Float.parseFloat(project.getPrice()));
		requestserialvo.setId(requestserialservice.getRequestSerial(user.getUserId() + ""));
		logger.info(
				"now the request serial's been generated and will send to the mq, then will be starting to check if the result shows"
						+ requestserialvo.getId());

		System.out.println("The requestserial vo's generated, the id is:" + requestserialvo.getId());
		orderproduce.sendDataToQueue("ordersCrowdfunding", requestserialvo);
		
		
    	System.out.println("*******now the vo before send to mq is:");
    	System.out.println(requestserialvo.getId());
    	System.out.println(requestserialvo.getPrice());
    	System.out.println(requestserialvo.getProjectid());
    	System.out.println(requestserialvo.getShares());
    	System.out.println(requestserialvo.getUserid());
    	System.out.println("*******end");
		
		//***above section's only for testing!!
		return "orders/order_confirm";
	}

	// 用户确认订单正确，并且要跳转支付的时候，需要更新一下ORDER里面的这条记录的状态为，确认，并跳转支付。
	// 更新状态的逻辑在这里写吧，更新完之后，应该是转到PAY里面
	@RequestMapping("/order/alreadyconfirmed")
	public String orderConfirmed(Orders order) {
		// System.out.println("Test to see if the order can be get in this
		// way:"+order.getOrderId());
		order.setStatus(1);
		order.setComment("now the user confirmed he/she needs to pay,time is:" + dateservice.getFullDate());
		System.out.println("begin to save into db with the new status");
		orderservice.saveOrUpdate(order); // update the status, then go to
											// pay...
		logger.info("now the user confirmed he/she needs to pay,time is:" + dateservice.getFullDate() + "order id is:"
				+ order.getOrderId());

		return "forward:/pay/orderpay";
	}
}
