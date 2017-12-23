package com.sjtu.SpringRelated;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest2 {
	public static void main(String[] argv){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beansAnnotation.xml");
		UserServiceBean2 usb = (UserServiceBean2)ctx.getBean("userServiceBean2");
		System.out.println(usb.getUserName());
		System.out.println(usb.getUserDao1().showUser());
	}
}
