package com.crowdfunding.sjtu.AQM;

import com.caucho.hessian.client.HessianProxyFactory;
import com.crowdfunding.sjtu.Vo.NodeInfomation;
import com.crowdfunding.sjtu.Vo.RequestSerialVO;
import com.crowdfunding.sjtu.model.Orders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;

import NetworkRelated.NodeInfo;

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
	private static ServerSocket ss = null;
	private Logger logger = Logger.getLogger(this.getClass());

	private ArrayList<NodeInfomation> nodesInfo = new ArrayList<NodeInfomation>();

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
	public void startTCPServer() throws ClassNotFoundException {
		try {
			ss = new ServerSocket(8888);
			bServerStarted = true;
			System.out.println("TCP server started, waiting for nodes info to update the array list");
			new Thread(new StatusCheck()).start(); //status check,print out the size information
			new Thread(new GetCPUUsageThread()).start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getCpuUsage() throws IOException, ClassNotFoundException{
		ServerSocket ss = new ServerSocket(8820);
		Socket s = ss.accept();
		ObjectInputStream oos = new ObjectInputStream(s.getInputStream());
		NodeInfomation ni = (NodeInfomation) oos.readObject();
		System.out.println(ni.getNodeName());
		System.out.println(ni.getCpuUsage());
	}
	
	// 每收到一个链接就启一个线程进行读取CPU数据，并保存到nodesInfo节点里面
	private class GetCPUUsageThread implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while (bServerStarted) {
				Socket s = null;
				try {
					s = ss.accept();
					System.out.println("a new client connected!");
					ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
					NodeInfomation mni = (NodeInfomation) ois.readObject();
					boolean bfound=false;
					for (int i=0;(i<nodesInfo.size()) && (bfound==false);i++){
						bfound=false;
						if (nodesInfo.get(i).getNodeName().equals(mni.getNodeName())){
							 // System.out.println("found the nodes, update it!");
							bfound = true;
							synchronized(nodesInfo){
								nodesInfo.get(i).setCpuUsage(mni.getCpuUsage()); //更新
							}
						}
					}
					if (!bfound){
						 // System.out.println("not found the nodes, add it!");
						synchronized(nodesInfo){
							nodesInfo.add(mni); // 存入节点列表	
						}
					}
					ois.close();
					s.close();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
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
				RequestSerialVO vo = jsonObjectMapper.readValue(body, RequestSerialVO.class);
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
				// nodesInfo.get(i).setIntConnection(nodesInfo.get(i).getIntConnection()-1);									
				System.out.println("Rpc call finished!");
			}
		};
		// 挑选最新的服务器，处理，启动新线程！服务器进程加一
		channel.basicConsume(queueName, true, consumer);
		
	}

	private class HessianDealWithOrder implements Runnable {
		private RequestSerialVO vo=null;
		private String url = "http://10.62.150.16:9090/Crowdfunding/remote/OrderService"; // need
		private String urltest = "http://10.62.150.16:9090/Crowdfunding/remote/test"; // need
		public HessianDealWithOrder(RequestSerialVO vo, String nodesName) {
			this.vo = vo;
			System.out.println("Check if the vo's type is what?"+vo.getClass());
			System.out.println(vo.toString());
			System.out.println(vo.getId());
			System.out.println(vo.getPrice());
			System.out.println(vo.getShares());
			System.out.println(vo.getUserid());
			this.url = url.replace("localhost", nodesName);
		}
		@Override
		public void run() {
			HessianProxyFactory factory = new HessianProxyFactory();
			
			IHSTest test; //test 
			try {
				System.out.println("In thread, start to RPC call!--Test--test  = (IHSTest) factory.create(IHSTest.class,urltest);");
				test  = (IHSTest) factory.create(IHSTest.class,urltest);
				System.out.println(test.hello());
				System.out.println("RPC call test end.--System.out.println(test.hello());");
				System.out.println("In thread, start to RPC call!");
				IOrderService os = (IOrderService) factory.create(IOrderService.class,url);
				System.out.println("Now begin to call os.saveOrder(vo)!");
				if ( os==null || vo==null){
					System.out.println("Check if the os or vo's null to displ errors!");
				}
/*				Orders order=new Orders();
				order.setComment("dd");
				order.setProjectId(1);
				order.setCreateDateTime("ddd");
				order.setShares(5);
				order.setStatus(1);
				order.setTotalAmount(44f);
				os.saveOrder(order);*/
				os.saveOrderBasedOnSerial(vo); // process
				System.out.println("RPC call end, in thread!");
			} catch (MalformedURLException e) {

				e.printStackTrace();
			}
		}
	}
private class StatusCheck implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			System.out.println("System status!");
			System.out.println("Client Size"+nodesInfo.size());
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
	public static void main(String[] argv) throws Exception {
		ManualReceiveMQMessage mm = new ManualReceiveMQMessage();
		// 启动CPU进程，获得节点，存到数组里
		mm.connectToMQ();
		mm.receive();
		mm.startTCPServer();
		// mm.getCpuUsage(); // 开始TCP服务器
	}
}
