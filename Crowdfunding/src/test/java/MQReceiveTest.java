import java.io.IOException;
import java.net.ServerSocket;

import org.apache.log4j.Logger;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class MQReceiveTest {
	private static final String EXCHANGE_NAME = "orders_exchange";
	private static final String routingKey = "ordersCrowdfunding";
	private static ConnectionFactory factory = null;
	private static Connection connection = null;
	private static Channel channel = null;
	private static String queueName = null;
	private boolean bServerStarted = false;
	private static ServerSocket ss = null;
	private static int threadCount = 0;
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
		 * if (argv.length < 1){ System.err. println(
		 * "Usage: ReceiveLogsDirect [info] [warning] [error]"); System.exit(1);
		 * }
		 */
		channel.queueBind(queueName, EXCHANGE_NAME, routingKey);
		/*
		 * for(String severity : argv){ channel.queueBind(queueName,
		 * EXCHANGE_NAME, severity); }
		 */
	}

	public static void main(String[] args) throws Exception {
		MQReceiveTest m = new MQReceiveTest();
		m.connectToMQ();
		m.receive();

	}

	private class ProcessQueue implements Runnable {
		private String strMessage;

		ProcessQueue(String message) {
			this.strMessage = message;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("Dealing with " + strMessage);
			synchronized (this) {
				System.out.println("Total thread++" + threadCount++);
			}
			try {
				Thread.sleep((long) (Math.random() * 5000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			synchronized (this) {
				System.out.println("Total thread--" + threadCount--);
			}
			System.out.println(strMessage + "Dealt end");
		}
	}

	public void receive() throws IOException {
		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				try {
					System.out.println("received, now restart new thread to deal with it!" + message);
					if (threadCount < 150){
						new Thread(new ProcessQueue(message)).start();						
					} else {
						System.out.println("NOw the client's very busy, need takes some break to deal with the message!+Sleep(1000)");
						try {
							Thread.sleep(1000);
							new Thread(new ProcessQueue(message)).start();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				} finally {
					System.out.println(message + "[x] Done");
					channel.basicAck(envelope.getDeliveryTag(), false);
				}
			}
		};
		// 挑选最新的服务器，处理，启动新线程！服务器进程加一
		channel.basicConsume(queueName, false, consumer);
		System.out.println("One message acknowledged--channel.basicConsume(queueName, true, consumer);");
	}
}
