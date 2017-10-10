import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

import NetworkRelated.NodeInfo;

public class ChatServer {
	private boolean bStarted = false;
	private ArrayList<Client> clients = new ArrayList<Client>();

	public static void main(String[] args) {
		new ChatServer().start();
	}

	public void start() {
		try {
			ServerSocket ss = new ServerSocket(8888);
			bStarted = true;
			while (bStarted) {
				Socket s = ss.accept();
				Client c = new Client(s);
				System.out.println("a client connected!");
				new Thread(c).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
				NodeInfo ni;
				HashMap mni;
				try {
					//ni = (NodeInfo) ois.readObject();
					mni = (HashMap) ois.readObject();
					System.out.println(mni.get("NodeName"));
					System.out.println(mni.get("CPU"));
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

}
