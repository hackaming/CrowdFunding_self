package com.crowdfunding.sjtu.MQ;

import javax.annotation.Resource;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderProduceImpl implements OrderProduce{
    @Autowired
    private AmqpTemplate amqpTemplate;
	
	@Override
	public void sendDataToQueue(String queueKey, Object object) {
		// TODO Auto-generated method stub
		amqpTemplate.convertAndSend(queueKey,object);
	}

}
