package com.crowdfunding.sjtu.controller;

import org.apache.commons.mail.EmailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.crowdfunding.sjtu.service.MailService;

@Controller
public class EmailController {
	@Autowired
	private MailService mailservice;
	
	@RequestMapping("/mailsend")
	public String mailSend() throws EmailException {
		mailservice.sendMail();
		return "email/mail_success";
	}
}
