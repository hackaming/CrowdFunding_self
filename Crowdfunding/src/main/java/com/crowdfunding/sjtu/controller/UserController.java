package com.crowdfunding.sjtu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
	
	@RequestMapping("/index")
	public String getHome(){
		return "/index";
	}

}
