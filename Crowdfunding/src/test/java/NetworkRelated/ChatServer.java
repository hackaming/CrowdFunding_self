package NetworkRelated;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.log4j.Logger;

public class ChatServer {
	private ServerSocket ss = null;
	private Logger logger = Logger.getLogger(this.getClass());
	private ArrayList<Client> clients = new ArrayList<Client>();
	
	public static void main(String argv[]){
		ChatServer cs = new ChatServer();
		cs.start();
		cs.serve();
	}
	
	public void start(){
		try {
			ss = new ServerSocket(9999);   //start the server
			logger.info("Chat's started!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("Can't start chat Server!");
			e.printStackTrace();
		}
	}
	public void serve(){ //started to serve,accept client and dispatch it.
		while (true){
			try {
				Socket socket = ss.accept();
				logger.info("New client's connected" + socket.getRemoteSocketAddress());
				Client c = new Client();
				c.setSocket(socket);
				c.setStatus(true);
				clients.add(c);
				new Thread(new ChatThread(c)).start();
				//start a new thread to deal with the client....add code below
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
