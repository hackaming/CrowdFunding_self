package com.crowdfunding.sjtu.AQM;

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

		System.out.println(message.getBody().toString());

		// logger.info("Crowdfundng receive message------->:{}",
		// message.getBody());
		Object o = convert.fromMessage(message);
		System.out.println("******Object o = convert.fromMessage(message);******");
		System.out.println(o);
		System.out.println("******System.out.println(o.toString());***********");
		System.out.println(o.toString());

		System.out.println("See if the object o is:" + o == null);

		System.out.println("********END******");
		RequestSerialVO vo = (RequestSerialVO) convert.fromMessage(message);
		System.out.println("now show the message id:" + vo.getId());
		showVO(vo);

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