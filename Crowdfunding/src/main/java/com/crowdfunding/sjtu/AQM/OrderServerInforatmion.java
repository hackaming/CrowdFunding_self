package com.crowdfunding.sjtu.AQM;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import com.crowdfunding.sjtu.Vo.NodeInfomation;

//TCP client test, the server's Server.java
public class OrderServerInforatmion {
	private static Socket s = null;
	private static HashMap hm=new HashMap();
	public static void main(String argv[]) throws UnknownHostException, IOException{
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				try {
					writeClass();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}, 500, 10000);
	}
	public static void writeInt() throws IOException{
		DataOutputStream dos = new DataOutputStream(s.getOutputStream());
		dos.writeInt(100);
		dos.flush();
		dos.close();
	}
	public static void loadNodesFromConfiguration(){
		hm.put("nodename", "10.62.150.33");
	}
	public static void writeObject() throws IOException{
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		hm.put("cpu", 0.0);
		oos.writeObject(hm);
		oos.flush();
		oos.close();
	}
	public static void writeClass() throws IOException{
		s = new Socket("10.62.150.7",8888); //与服务器建立连接，目前运行在本机
		System.out.println("Connected to the server with 192.168.0.106:8888");
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		NodeInfomation ni = new NodeInfomation();
		CpuUsage cpu = new CpuUsage();
		ni.setCpuUsage(cpu.getCpuRatioForWindows());
		ni.setNodeName("10.62.150.33"); //都跑在16上，要根据实际情况调用
		oos.writeObject(ni);
		oos.flush();
		oos.close();
	}
}
