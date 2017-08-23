package com.crowdfunding.sjtu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crowdfunding.sjtu.service.CommandService;

@Controller
public class CommandController {
	@Autowired
	private CommandService commandservice;
	
	@RequestMapping("/commandTest")
	public String commandTest() throws Exception{
		commandservice.approve();
		return null;
	}

}
