package com.crowdfunding.sjtu.AQM;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;




public class ReadQueueAndDistribute implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());  
    @Override  
    public void onMessage(Message message) {  
        logger.info("Crowdfundng receive message------->:{}", message);  
    }  
}