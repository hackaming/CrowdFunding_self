package NetworkRelated;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

public class ChatThread_out implements Runnable{
	private Client client;
	Logger logger;
	public ChatThread_out(Client client){
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
			while (client.isStatus()){ //一直连着
				String strdis = dis.readUTF();
				logger.info("Message that received from the client:"+strdis);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.info("exception shows, needs to delete the client?");
			e.printStackTrace();
		} finally{
//如何通知服务器线程结束？？？？
			if (null != dis){
				try {
					dis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != dos){
				try {
					dos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		// TODO Auto-generated method stub	
	}

}
