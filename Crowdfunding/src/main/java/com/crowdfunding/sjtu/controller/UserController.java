package com.crowdfunding.sjtu.controller;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crowdfunding.sjtu.model.User;
import com.crowdfunding.sjtu.service.IUserService;
import com.crowdfunding.sjtu.utility.IDateService;
import com.crowdfunding.sjtu.utility.MD5Util;

@Controller
public class UserController {
	
	@Autowired
	public IDateService dateService;
	@Autowired
	public IUserService userServie;
	@RequestMapping("/")
	public String getHome1(){
		return getHome();
	}
	
	@RequestMapping("/index")
	public String getHome(){
		return "project/projec_tlist";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "user/login";
	}
	
	@RequestMapping(value="/user/log",method = RequestMethod.POST)
	public String login1(HttpServletRequest req){
		String userName = req.getParameter("userName");
		String password = req.getParameter("password");
		//org.hibernate.QueryException: Not all named parameters have been set: [userName] [from User u where u.userName =:userName]
		//这个地方有BUG，暂时不知道什么原因，无法登录，报SQU QUERY ERROR，先放这里吧。
		if ( !userServie.login(userName, password) ){
			//login failure
			return "user/login_failure";
		} else {
			//login successfully
			return "user/login_success";			
		}
	}
	
	//get the parameters and save it into db and then return a succ/failure page to the user
	@RequestMapping(value="/user/reg",method = RequestMethod.POST)
	public String reg(HttpServletRequest req, HttpServletResponse resp) throws NoSuchAlgorithmException, UnsupportedEncodingException{
		//1.把参数接收过来，2.调用SERVICE层，插入DB,3.这里为了简化，先直接调用DAO，但后面需要改正过来。
		User user = new User();
		if (!req.getParameter("password").equals(req.getParameter("password1"))){
			return "user/register_failure";
		}
		
		//需要加入判断用户ID是否存在的逻辑，还要思考一个问题，是不是把这些代码都放到服务层？要不然控制层会越来越多，直接调服务层就行了?
		
		
		
		//Debug user only
		System.out.println("registration called");
		user.setUserName(req.getParameter("userName"));
		user.setPassword(MD5Util.MD5Encrypt(req.getParameter("password")));
		user.setSex(req.getParameter("sex"));
		user.setCellPhone(req.getParameter("cellPhone"));
		//还需要设置DATETIME, STATUS
		user.setCreateDateTime(dateService.getStandardDate());
		userServie.saveUser(user);
		return "user/register_success";
	}
	
	//show the jsp form
	@RequestMapping("/register")
	public String register(){
		return "user/register";
	}
}
