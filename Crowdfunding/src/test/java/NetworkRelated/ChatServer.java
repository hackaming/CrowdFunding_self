package NetworkRelated;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class ChatServer {
	private ServerSocket ss = null;
	private Logger logger = Logger.getLogger(this.getClass());
	private ArrayList<Client> clients = new ArrayList<Client>();

	public static void main(String argv[]) {
		ChatServer cs = new ChatServer();
		cs.start();
		cs.serve();
	}

	public void start() {
		try {
			ss = new ServerSocket(9999); // start the server
			logger.info("Chat's started!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Can't start chat Server!");
			e.printStackTrace();
		}
	}

	public void serve() { // started to serve,accept client and dispatch it.
		int id = 0;
		while (true) {
			try {
				Socket socket = ss.accept();
				logger.info("New client's connected" + socket.getRemoteSocketAddress());
				Client c = new Client();
				c.setSocket(socket);
				c.setStatus(true);
				clients.add(c);
				new Thread(new ChatThread(c)).start();
				// start a new thread to deal with the client....add code below
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void sendMessageToAllClients(String str) {
		for (Client c : clients) {
			try {
				if (c.isStatus()) {
					DataOutputStream dos = new DataOutputStream(c.getSocket().getOutputStream());
					dos.writeUTF(str);
					dos.flush();
					logger.info("Forwarded the message to:"+c);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	private class ChatThread implements Runnable {
		private Client client;
		Logger logger;

		public ChatThread(Client client) {
			this.client = client;
			this.logger = Logger.getLogger(this.getClass());
		}

		@Override
		public void run() {
			DataInputStream dis = null;
			DataOutputStream dos = null;
			logger.info("new ChatThread is started!");
			try {
				dis = new DataInputStream(client.getSocket().getInputStream());
				dos = new DataOutputStream(client.getSocket().getOutputStream());
				while (client.isStatus()) { // 一直连着
					String strdis = dis.readUTF();
					logger.info("Message that received from the client:" + client + "Message:" + strdis);
					// now forward the message to the clients??
					sendMessageToAllClients(strdis);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				logger.info("exception shows, needs to delete the client?");
				clients.remove(client);
				logger.info(client + "is removed!");
				e.printStackTrace();
			} finally {
				// 如何通知服务器线程结束？？？？
				if (null != dis) {
					try {
						dis.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if (null != dos) {
					try {
						dos.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				logger.info("client's removed from the clients arraylist:" + client.toString());
				clients.remove(client);
			}
			// TODO Auto-generated method stub
		}

	}

}
