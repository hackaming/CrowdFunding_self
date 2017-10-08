package com.crowdfunding.sjtu.AQM;

import java.text.ParseException;

import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.crowdfunding.sjtu.Vo.RequestSerialVO;


public class ReadQueueAndDistribute implements MessageListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

    
	@Override
	public void onMessage(Message message) {
		System.out.println("test*************test");
		System.out.println(message.toString());
		 byte[] dd  = message.getBody();
		System.out.println("test*************test");
		System.out.println(dd);
		message.getBody();
		System.out.println(message.getBody().toString());
		
		try {
			Object o = JSONValue.parseWithException(message.getBody().toString());
			RequestSerialVO v = (RequestSerialVO) o;
			System.out.println(v.getId()+"###now it is the ID!");
		} catch (org.json.simple.parser.ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public void onMessage(Message message,String i){
		/*Object obj = amqpTemplate.receiveAndConvert();
		System.out.println(obj.toString()+"this is from amqptemplate.receiveandconvert()");*/
		System.out.println("From OnMessage!!");
		
		System.out.println("****message get in onmessage*******System.out.println(message.getBody());***");
		System.out.println(message.getBody());
		System.out.println("****message get in onmessage*******System.out.println(message.toString());***");
		System.out.println(message.toString());
		System.out.println("****message get in onmessage*******System.out.println(message.getMessageProperties());***");
		System.out.println(message.getMessageProperties());

/*		System.out.println(message.getBody().toString());
		try {
			Object o = JSONValue.parseWithException(message.toString());
			RequestSerialVO v = (RequestSerialVO) o;
			System.out.println(v.getId()+"is the id!");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		// logger.info("Crowdfundng receive message------->:{}",
		// message.getBody());

	}
	public void showVO(RequestSerialVO vo) {
		System.out.println("********now the vo get from mq is:");
		System.out.println(vo.getId());
		System.out.println(vo.getPrice());
		System.out.println(vo.getProjectid());
		System.out.println(vo.getShares());
		System.out.println(vo.getUserid());
		System.out.println("*******end");
	}
	
}