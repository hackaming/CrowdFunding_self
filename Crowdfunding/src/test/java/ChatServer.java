import java.io.DataInputStream;
import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

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
				clients.add(c);
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
		private boolean bConnected = false;

		public Client(Socket s) {
			this.s = s;
			try {
				dis = new DataInputStream(s.getInputStream());
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
				String str;
				try {
					str = dis.readUTF();
					System.out.println(str);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					try {
						if (dis != null)
							dis.close();
						bConnected=false;
						if (s != null)
							s.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}

			}
		}

	}

}
