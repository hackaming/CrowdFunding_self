package NetworkRelated;

import java.text.ParseException;

import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.crowdfunding.sjtu.Vo.RequestSerialVO;

public class ReadQueueAndDistribute implements MessageListener {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
    
	@Override
	public void onMessage(Message message) {
		Jackson2JsonMessageConverter jj = new Jackson2JsonMessageConverter();
		Object o = jj.fromMessage(message);
		
		RequestSerialVO vo = (RequestSerialVO) o;
		System.out.println("********now the vo get from mq is:");
		System.out.println(vo.getId());
		System.out.println(vo.getPrice());
		System.out.println(vo.getProjectid());
		System.out.println(vo.getShares());
		System.out.println(vo.getUserid());
		System.out.println("*******end");
		
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