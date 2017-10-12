package com.crowdfunding.sjtu.AQM;

import com.caucho.hessian.client.HessianProxyFactory;
import com.crowdfunding.sjtu.Vo.RequestSerialVO;
import com.crowdfunding.sjtu.Vo.ServerInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import com.crowdfunding.sjtu.Vo.ServerInfo;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;
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
	private Logger logger = Logger.getLogger(this.getClass());

	private ArrayList<ServerInfo> nodesInfo = new ArrayList<ServerInfo>();

	public String getBestNode() {
		Double base = 0.0;
		String nodes = "temp";
		for (int i = 0; i < nodesInfo.size(); i++) {
			if ( nodesInfo.get(i).getCpuUsage() * nodesInfo.get(i).getIntConnection()> base) {
				nodes = (String) nodesInfo.get(i).getNodeName();
			}
		}
		return nodes;
	}

	// 监听来自CPU发送客户端的连接
	public void startTCPServer() {
		try {
			ServerSocket ss = new ServerSocket(8888);
			bServerStarted = true;
			System.out.println("Started the TCP server wait to get the nodes Info");
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

	// 每收到一个链接就启一个线程进行读取CPU数据，并保存到nodesInfo节点里面
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
					//ServerInfo mni = (ServerInfo) ois.readObject();
					ServerInfo si = (ServerInfo) ois.readObject();
					boolean bfound=false;
					for (int i=0;(i<nodesInfo.size()) && (bfound==false);i++){
						bfound=false;
						if (nodesInfo.get(i).getNodeName().equals(si.getNodeName())){
							bfound = true;
							nodesInfo.get(i).setCpuUsage(si.getCpuUsage());
						}
					}
					if (!bfound){
						nodesInfo.add(si); // 存入节点列表	
					}
					System.out.println(si.getNodeName());
					System.out.println(si.getCpuUsage());
					System.out
							.println("The above node has been saved into the arrylist! from run in the client thread");
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

	// 连接mq
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

	// consider a inneral thread to get the cpu usage,and inneral thread to run
	// the rpc call,just simulate it!!
	public void receive() throws Exception { // 开始接收MQ的队列
		Jackson2JsonMessageConverter jj = new Jackson2JsonMessageConverter();

		final ObjectMapper jsonObjectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

		System.out.println("Start to monitor the message queue!");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				String message = new String(body, "UTF-8");
				System.out.println(
						" From handleDelivery[x] Received '" + envelope.getRoutingKey() + "':'" + message + "'");
				System.out.println("Now get the message" + message + "!");
				// Object o = jj.fromMessage(message);
				RequestSerialVO vo = jsonObjectMapper.readValue(body, RequestSerialVO.class);
/*				System.out.println("NOw show the request serial's id and price!");
				System.out.println(vo.getId());
				System.out.println(vo.getPrice());
				System.out.println(vo.getProjectid());
				System.out.println(vo.getShares());
				System.out.println(vo.getUserid());*/
				// RequestSerialVO vo = (RequestSerialVO) o;
				// System.out.println("now get the business server!");
				System.out.println(getBestNode() + " now get the best nodes!");
				String strBestNode=getBestNode();
				int i=0;
				while(i<nodesInfo.size()){ //改挑最佳的服务器，+1
					if (nodesInfo.get(i).equals(strBestNode)){
						synchronized(nodesInfo.get(i)){
							nodesInfo.get(i).setIntConnection(nodesInfo.get(i).getIntConnection()+1);							
						}
					}
					i++;
				}//这里要同步
				logger.info("加点log,开始处理的时候");
				System.out.println("Begin to hessian call!, still in handle delivery");
				new Thread(new HessianDealWithOrder(vo,strBestNode)).start(); //开始启用新线程处理
				synchronized(nodesInfo.get(i)){ //调用完毕，-1
					nodesInfo.get(i).setIntConnection(nodesInfo.get(i).getIntConnection()-1);							
				}
				System.out.println("Rpc call finished!");

			}
		};
		// 挑选最新的服务器，处理，启动新线程！服务器进程加一
		channel.basicConsume(queueName, true, consumer);
	}

	private class HessianDealWithOrder implements Runnable {
		private RequestSerialVO vo;
		private String url = "http://localhost:8080/Crowdfunding/remote/OrderService"; // need
		public HessianDealWithOrder(RequestSerialVO vo, String nodesName) {
			this.vo = vo;
			this.url = url.replace("localhost", nodesName);
		}

		@Override
		public void run() {
			HessianProxyFactory factory = new HessianProxyFactory();
			IOrderService os;
			try {
				System.out.println("In thread, start to RPC call!");
				os = (IOrderService) factory.create(IOrderService.class,
						"http://localhost:8080/Crowdfunding/remote/OrderService");
				os.saveOrder(vo); // process
				System.out.println("RPC call end, in thread!");
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
		}
	}

	public static void main(String[] argv) throws Exception {
		ManualReceiveMQMessage mm = new ManualReceiveMQMessage();
		// 启动CPU进程，获得节点，存到数组里
		mm.connectToMQ();
		mm.receive();
		mm.startTCPServer(); // 开始TCP服务器
	}
}
