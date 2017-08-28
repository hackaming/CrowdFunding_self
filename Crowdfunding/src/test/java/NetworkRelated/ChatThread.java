package NetworkRelated;

import java.net.Socket;

import org.apache.log4j.Logger;

public class ChatThread implements Runnable{
	private Client client;
	Logger logger;
	public ChatThread(Client client){
		this.client = client;
		this.logger = Logger.getLogger(this.getClass());
	}
	
	@Override
	public void run() {
		logger.info("new ChatThread is started!");
		
		// TODO Auto-generated method stub
		
		
	}

}
