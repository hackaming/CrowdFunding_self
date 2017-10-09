import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

import NetworkRelated.CpuUsage;



public class ChatClient {
	Socket s = null;
	DataOutputStream dos = null;

	public static void main(String argv[]) {
		new ChatClient().connect();
	}

	public void connect() {
		try {
			s = new Socket("127.0.0.1", 8888);
			System.out.println("connected!");
			dos = new DataOutputStream(s.getOutputStream());
			CpuUsage cpu = new CpuUsage();
			Double bUsage = cpu.getCpuRatioForWindows();
			dos.writeUTF(bUsage.toString());
			dos.flush();
			dos.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void disconnect() {
		try {
			dos.close();
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
