package NetworkRelated;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import com.crowdfunding.sjtu.Vo.NodeInfomation;

//用来获取CPU，并传给服务器端，做负载均衡用的！！

public class NodeClient {
	private static Socket s = null;

	private static ObjectOutputStream oos = null;
	private static NodeInfomation mni = null;

	public static void main(String argv[]) {
		NodeClient cc = new NodeClient();
		NodeClient.loadConfiguration();
		NodeClient.connect();
		
		Timer timer = new Timer();
/*		timer.schedule(new TimerTask() {
			public void run() {
				loadConfiguration();
				connect();
			}
		}, 500, 10000);*/
	}

	public static void loadConfiguration() {
		// load the nodes name!
		mni = new NodeInfomation();
		mni.setNodeName("SHG076CR33723HH");
	}

	public static void connect() {
		try {
			s = new Socket("127.0.0.1", 8819);
			ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
			NodeInfomation ni = new NodeInfomation();
			CpuUsage cpu = new CpuUsage();
			ni.setNodeName("xianmingtestNode");
			oos.writeObject(ni);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
