package NetworkRelated;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

public class SocketClientTest {
	public static void main(String argv[]) throws UnknownHostException, IOException{
		Socket s = new Socket("10.62.150.33", 19999);
		NodeInfo ni = new NodeInfo();
		ni.setCpuUsage(0.0);
		ni.setStrNodeName("xianmingtestnode");
		
		HashMap hm = new HashMap();
		hm.put("nodename", "xianmingtestnode");
		hm.put("cpu", 0.0);
		
		ObjectOutputStream ois= new ObjectOutputStream(s.getOutputStream());
		ois.writeObject(hm);
		ois.flush();
	}
}
