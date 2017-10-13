package NetworkRelated;
import java.io.DataInputStream;
//tcp write object test,server
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

import com.crowdfunding.sjtu.Vo.NodeInfomation;

public class Server {
	private static Socket s;
	public static void main(String argv[]) throws IOException, ClassNotFoundException{
		ServerSocket ss = new ServerSocket(1928);
		s = ss.accept();
		readClass();
	}
	public static void readInt() throws IOException{
		DataInputStream dis = new DataInputStream(s.getInputStream());
		int i = dis.readInt();
		System.out.println(i);
	}
	public static void readObject() throws IOException, ClassNotFoundException{
		ObjectInputStream oos = new ObjectInputStream(s.getInputStream());
		HashMap hm = (HashMap) oos.readObject();
		System.out.println(hm.get("nodename"));
		System.out.println(hm.get("cpu"));
	}
	public static void readClass() throws ClassNotFoundException, IOException{
		ObjectInputStream oos = new ObjectInputStream(s.getInputStream());
		NodeInfomation ni = (NodeInfomation) oos.readObject();
		System.out.println(ni.getNodeName());
		System.out.println(ni.getCpuUsage());
		
	}
}
