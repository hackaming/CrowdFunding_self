package com.crowdfunding.sjtu.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.crowdfunding.sjtu.model.Orders;
import com.crowdfunding.sjtu.model.User;
import com.crowdfunding.sjtu.service.IOrderService;
import com.crowdfunding.sjtu.service.IProjectService;
import com.crowdfunding.sjtu.service.IUserService;
import com.crowdfunding.sjtu.utility.IDateService;
import com.crowdfunding.sjtu.utility.MD5Util;

@Controller
public class UserController {
	
	@Autowired
	private IDateService dateService;
	@Autowired
	private IUserService userServie;
	Logger logger = Logger.getLogger(this.getClass());
	
	@Autowired
	private IOrderService orderservice;
	@Autowired
	private IProjectService projectservice;
	

	
	@RequestMapping("/")
	public String getHome1(){
		return getHome();
	}
	
	@RequestMapping("/index")
	public String getHome(){
		return "project/project_list";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "user/login";
	}
	@RequestMapping("/checkmongodb")
	public String checkmongodb(){
		 Logger logger1 = Logger.getLogger("MongoDB"); 
		 Logger logger = Logger.getLogger(this.getClass());
		for (int i=0;i<10;i++){
			logger1.info("{'info':123}");
			logger1.error("{'error':123}");
			logger1.warn("{'warn':123}");
			logger1.debug("{'debug':123}");
			logger1.info("hello mongo");
		}
		for (int i=0;i<10;i++){
			logger.info("{'rootinfo':123}");
			logger.error("{'rooterror':123}");
			logger.warn("{'rootwarn':123}");
			logger.debug("{'rootdebug':123}");
			logger.info("hello mongo");
		}
			
		return "user/login";
	}
	@RequestMapping(value="/user/log",method = RequestMethod.POST)
	public String login1(@RequestParam String userName,@RequestParam String password, HttpSession session){
		
		User u = userServie.getUserByName(userName);	
		if ( !userServie.login(userName, password) ){ 
			//login failure
			return "user/login_failure";
		} else {

			//return "redirect:/projectlist";			//also works
			logger.info("user id is"+u.getUserId());
			logger.info("user name is"+u.getUserName() + "now set it into session");
			session.setAttribute("user", u);   
			return "forward:/projectlist";
		}
	}
	//log out
	@RequestMapping(value="/user/logout")
	public String userLogout(HttpSession session)
	{
		if (null != session.getAttribute("user")){
			session.removeAttribute("user");
		}
		return "user/login";
	}
	
	//get the parameters and save it into db and then return a succ/failure page to the user
	@RequestMapping(value="/user/reg",method = RequestMethod.POST)
	public String reg(HttpServletRequest req, HttpServletResponse resp,@RequestParam("password") String password,@RequestParam("password1") String password1) throws NoSuchAlgorithmException, UnsupportedEncodingException{


		if (!password.equals(password1)){
			return "user/register_failure";
		}
		User user = new User();		
		//
		//Debug user only
		logger.info("registration called");
		user.setUserName(req.getParameter("userName"));
		user.setPassword(MD5Util.MD5Encrypt(req.getParameter("password")));
		user.setSex(req.getParameter("sex"));
		user.setCellPhone(req.getParameter("cellPhone"));
	
		user.setCreateDateTime(dateService.getStandardDate());
		userServie.saveUser(user);
		logger.info("user's saved");
		return "user/register_success";
	}
	
	//show the jsp form
	@RequestMapping("/register")
	public String register(){
		return "user/register";
	}
	
	@RequestMapping("/usercenter")
	public String userCenter(HttpServletRequest request ,HttpSession session,ModelMap modelmap){
		User u = (User) session.getAttribute("user");
		if (null == u){
			return "user/login";
		}
		
		List<Orders> orders = orderservice.getOrdersByUserId(u.getUserId());
		
		modelmap.addAttribute("orders", orders);
		return "user/user_center";
	}
}
