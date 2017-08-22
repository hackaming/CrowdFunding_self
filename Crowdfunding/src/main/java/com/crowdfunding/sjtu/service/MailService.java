package com.crowdfunding.sjtu.service;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.crowdfunding.sjtu.model.Mail;

@Service
public class MailService {
	public void getParameters(){
		//get e-mail/server/accounts/configuration
	}
	public void sendMail() throws EmailException{
		Mail mail = new Mail();
		Email email = new SimpleEmail();
		email.setHostName(mail.getHostName());
		email.setSmtpPort(Integer.parseInt(mail.getHostPort()));
		email.setAuthentication("lichao197808", "Hack1ming");
		email.setSSL(mail.isSslOnConnect());
		email.setFrom(mail.getFrom());
		email.setSubject(mail.getSubject());
		email.setMsg(mail.getMessage());
		email.addTo(mail.getTo());
		email.send();
		Logger logger = Logger.getLogger(this.getClass());
		logger.info("e-mail's send out, please check");
	}
}
