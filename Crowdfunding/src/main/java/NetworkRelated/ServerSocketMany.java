package NetworkRelated;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocketMany {
	public static void main(String argv[]) {
		List<ServerThread> serverThreads = new ArrayList<ServerThread>();
		
		try{
			ServerSocket ss = new ServerSocket(9997);
			System.out.println("Server:Server's started, listening port at:9999");
			while (true){
				System.out.println("Server:Read to accept client request.");
				Socket s = ss.accept();
				System.out.println("Server:Request received, starts new thread to deal with.");
				ServerThread st = new ServerThread(s);
				serverThreads.add(st);
				st.start();
				System.out.println("Server:Thread " + serverThreads.size() + " is dealing with the request.");
			}
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
