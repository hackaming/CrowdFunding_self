package com.sjtu.SpringRelated;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringTest {
	public static void main(String[] argv){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
	}
}
