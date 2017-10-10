package com.crowdfunding.sjtu.AQM;



import com.crowdfunding.sjtu.Vo.RequestSerialVO;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;

//后来写的手工读队列，并获得CPU，这里还需要，远程调用并负载均衡！！！手工执行，2个项目！！


public class ManualReceiveMQMessage {
	private static final String EXCHANGE_NAME = "orders_exchange";
	private static final String routingKey = "ordersCrowdfunding";
	private static ConnectionFactory factory = null;
	private static Connection connection = null;
	private static Channel channel = null;
	private static String queueName = null;
	private boolean bServerStarted = false;
	
	private ArrayList <HashMap> nodesInfo = new ArrayList <HashMap>();

	public String getBestNode(){
		Double base = 0.0;
		String nodes = "temp";
		for (int i=0;i<nodesInfo.size();i++){
			if ((Double)nodesInfo.get(i).get("CPU")>base){
				nodes = (String)nodesInfo.get(i).get("NodeName");
			}
		}
		return nodes;
	}
	//监听来自CPU发送客户端的连接
	public void startTCPServer() {
		try {
			ServerSocket ss = new ServerSocket(8888);
			bServerStarted = true;
			while (bServerStarted) {
				Socket s = ss.accept();
				Client c = new Client(s);
				System.out.println("a client connected!");
				new Thread(c).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
//每收到一个链接就启一个线程进行读取CPU数据，并保存到nodesInfo节点里面
	private class Client implements Runnable {
		private Socket s;
		private DataInputStream dis = null;
		private ObjectInputStream ois = null;
		private boolean bConnected = false;

		public Client(Socket s) {
			this.s = s;
			try {
				ois = new ObjectInputStream(s.getInputStream());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bConnected = true;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (bConnected) {
				try {
					// ni = (NodeInfo) ois.readObject();
					HashMap mni = (HashMap) ois.readObject();
					nodesInfo.add(mni); //存入节点列表
					System.out.println(mni.get("NodeName"));
					System.out.println(mni.get("CPU"));
					System.out.println("The above node has been saved into the arrylist! from run in the client thread");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (dis != null)
							dis.close();
						bConnected = false;
						if (s != null)
							s.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
			System.out.println("Now the run ends, the client ends?");
		}

	}
//连接mq
	public void connectToMQ() throws Exception {
		factory = new ConnectionFactory(); 
		factory.setHost("10.62.150.33"); //这些东西要配置文件化
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

	// consider a inneral thread to get the cpu usage,and inneral thread to run
	// the rpc call,just simulate it!!
	public void receive() throws Exception {   //开始接收MQ的队列
		Jackson2JsonMessageConverter jj = new Jackson2JsonMessageConverter();
		
		final ObjectMapper jsonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
		
		System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(" From handleDelivery[x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
				System.out.println("Now get the message" + message + "!");
				//Object o = jj.fromMessage(message);
				RequestSerialVO vo=jsonObjectMapper.readValue(body, RequestSerialVO.class);
				System.out.println("NOw show the request serial's id and price!");
				System.out.println(vo.getId());
				System.out.println(vo.getPrice());
				System.out.println(vo.getProjectid());
				System.out.println(vo.getShares());
				System.out.println(vo.getUserid());
				//RequestSerialVO vo = (RequestSerialVO) o;
				//System.out.println("now get the business server!");
				System.out.println(getBestNode()+"Pay attention!!! From handleDelivery");
				System.out.println("Now the business server's connection+1");
				System.out.println(
						"Now start new thread to rpc cay by using the server, otherwise it will be synchnorize");
				System.out.println(
						"Need to consider when to change the business server's connection!need to -1 when it finished!");
			}
		};
		channel.basicConsume(queueName, true, consumer);
		// 挑选最新的服务器，处理，启动新线程！服务器进程加一
	}

	public static void main(String[] argv) throws Exception {
		ManualReceiveMQMessage mm = new ManualReceiveMQMessage();
		// 启动CPU进程，获得节点，存到数组里
		mm.connectToMQ();
		mm.receive();
		mm.startTCPServer(); //开始TCP服务器
	}
}
