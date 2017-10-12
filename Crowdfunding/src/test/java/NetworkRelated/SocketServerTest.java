package NetworkRelated;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class SocketServerTest {
	private static NodeInfo nis; 
	private static HashMap hm;
	public static void start() throws IOException, ClassNotFoundException{
		ServerSocket ss= new ServerSocket(5678);
		System.out.println("Server started.");
		Socket s = ss.accept();
		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		//hm = (HashMap) ois.readObject();
		nis = (NodeInfo) ois.readObject();
	}
	public static void main(String argv[]) throws ClassNotFoundException, IOException{
		start();
		System.out.println(nis.getStrNodeName());
		System.out.println(nis.getCpuUsage());
		//System.out.println(hm.get("nodename"));
		//System.out.println(hm.get("cpu"));
		System.out.println("Server end.");
	}
}
