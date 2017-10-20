import java.io.IOException;
import java.net.ServerSocket;

import org.apache.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class MQSendTest {
	private static final String EXCHANGE_NAME = "orders_exchange";
	private static final String routingKey = "ordersCrowdfunding";
	private static ConnectionFactory factory = null;
	private static Connection connection = null;
	private static Channel channel = null;
	private static String queueName = null;
	private boolean bServerStarted = false;
	private static ServerSocket ss = null;
	private Logger logger = Logger.getLogger(this.getClass());

	public void connectToMQ() throws Exception {
		factory = new ConnectionFactory();
		factory.setHost("10.62.150.33"); // 这些东西要配置文件化
		factory.setVirtualHost("crowdfunding");
		factory.setUsername("crowdfunding");
		factory.setPassword("crowdfunding");
		factory.setPort(5672);

		connection = factory.newConnection();
		channel = connection.createChannel();

		channel.exchangeDeclare(EXCHANGE_NAME, "direct", true);

		// String queueName = channel.queueDeclare().getQueue();
		queueName = "orders";

		/*
		 * if (argv.length < 1){ System.err.
		 * println("Usage: ReceiveLogsDirect [info] [warning] [error]");
		 * System.exit(1); }
		 */
		channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
		/*
		 * for(String severity : argv){ channel.queueBind(queueName,
		 * EXCHANGE_NAME, severity); }
		 */
	}

	public static void main(String[] args) throws Exception {
		MQSendTest m = new MQSendTest();
		m.connectToMQ();
		m.send();
	}

	public void send() throws IOException {
		System.out.println("Begin to send out 1000 messages");
		for (int i = 0; i < 10000; i++) {
			String str = "Hello World " + i;
			channel.basicPublish(EXCHANGE_NAME, routingKey, null, str.getBytes());
			System.out.println(str + "sent out");
		}
		System.out.println("1000 message sent out!");
	}
}
