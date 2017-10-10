import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import NetworkRelated.CpuUsage;
import NetworkRelated.NodeInfo;

//用来获取CPU，并传给服务器端，做负载均衡用的！！

public class NodeClient {
	private static Socket s = null;
	
	private static ObjectOutputStream oos = null;
	private static NodeInfo ni =null;
	private static HashMap  mni = null;

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
	ni = new NodeInfo();
	ni.setStrNodeName("OrderNode1"); ///later need to change it to load data from configuration files
	mni = new HashMap();
	mni.put("NodeName", "OrderNode1"); //need to use Properties class to load properties http://www.cnblogs.com/bakari/p/3562244.html
	
}
	public static void connect() {
		try {
			s = new Socket("127.0.0.1", 8888);
			System.out.println("connected! From ChatClient!");
			
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			CpuUsage cpu = new CpuUsage();
			Double bUsage = cpu.getCpuRatioForWindows();
			//ni.setCpuUsage(bUsage);
			mni.put("CPU", bUsage);
			
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
