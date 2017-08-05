package com.crowdfunding.sjtu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.crowdfunding.sjtu.model.User;
import com.crowdfunding.sjtu.service.IUserService;
import com.crowdfunding.sjtu.utility.IDateService;

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
		return "project/projectlist";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "login";
	}
	
	//get the parameters and save it into db and then return a succ/failure page to the user
	@RequestMapping(value="/reg",method = RequestMethod.POST)
	public String reg(HttpServletRequest req, HttpServletResponse resp){
		//1.把参数接收过来，2.调用SERVICE层，插入DB,3.这里为了简化，先直接调用DAO，但后面需要改正过来。
		User user = new User();
		//Debug user only
				
		user.setUserName(req.getParameter("userName"));
		user.setPassword(req.getParameter("password"));
		String password1 = req.getParameter("password1");
		user.setSex(req.getParameter("sex"));
		user.setCellPhone(req.getParameter("cellPhone"));
		//还需要设置DATETIME, STATUS
		user.setCreatDate(dateService.getStandardDate());
		
		if (!user.getPassword().equals(password1)){
			return "user/register_failure";
		}
		userServie.saveUser(user);
		return "user/register_success";
	}
	
	//show the jsp form
	@RequestMapping("/register")
	public String register(){
		return "user/register";
	}
}
