package com.crowdfunding.sjtu.AQM;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.crowdfunding.sjtu.Vo.ServerInfo;

import NetworkRelated.CpuUsage;
import NetworkRelated.NodeInfo;

//用来获取CPU，并传给服务器端，做负载均衡用的！！

public class NodeClient {
	private static Socket s = null;
	
	private static ObjectOutputStream oos = null;
	private static ServerInfo  mni = new ServerInfo();

	public static void main(String argv[]) {
		NodeClient cc = new NodeClient();
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				loadConfiguration();
				connect();
			}
		}, 500, 10000);
	}
public static void loadConfiguration(){
	//load the nodes name!
	mni.setNodeName("SHG076CR33723HH");
}
	public static void connect() {
		try {
			s = new Socket("127.0.0.1", 8888);
			System.out.println("connected! From ChatClient!");
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			CpuUsage cpu = new CpuUsage();
			Float bUsage = cpu.getCpuRatioForWindows();
			//ni.setCpuUsage(bUsage);
			ServerInfo mni = new ServerInfo();
			mni.setCpuUsage(bUsage);
			mni.setNodeName("XianmingTest");
			
			oos.writeObject(mni);
			oos.flush();
			oos.close();
/*			dos.writeUTF(bUsage.toString());
			dos.flush();
			dos.close();*/
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void disconnect() {
		try {
			oos.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
}
