package com.crowdfunding.sjtu.MQ;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crowdfunding.sjtu.Vo.RequestSerialVO;

@Service
public class OrderProduceImpl implements OrderProduce{
    @Autowired
    private AmqpTemplate amqpTemplate;
	
	@Override
	public void sendDataToQueue(String queueKey, Object object) {
		// TODO Auto-generated method stub
		RequestSerialVO vo =(RequestSerialVO) object;
/*		
    	System.out.println("********now the vo get sendDataToQueue in mq is:");
    	System.out.println(vo.getId());
    	System.out.println(vo.getPrice());
    	System.out.println(vo.getProjectid());
    	System.out.println(vo.getShares());
    	System.out.println(vo.getUserid());
    	System.out.println("*******end!!!is about to send!!");*/
    	
		amqpTemplate.convertAndSend(queueKey,object);
		
		System.out.println("***amqpTemplate.convertAndSend(queueKey,object)****");
	}

}
