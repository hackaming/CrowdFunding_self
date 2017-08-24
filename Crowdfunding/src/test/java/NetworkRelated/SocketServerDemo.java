package NetworkRelated;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServerDemo {
	public  static void main(String argv[]){
		try{
			ServerSocket ss = new ServerSocket(9999);
			System.out.println("Server's starting, port's 9999!");
			Socket socket = ss.accept();
			//Socket's a class that has a lot of properties
			InputStream is = socket.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String data = null;
			while((data=br.readLine())!=null){
				System.out.println("In Server side:Data received from the client:" + data);
			}		
			//需要客端和服务器一起写吧？要不然会出错
			socket.shutdownInput();
			OutputStream os = socket.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			pw.write("The data's been received");
			pw.flush();
			
			socket.shutdownOutput();
			pw.close();
			br.close();
			isr.close();
			is.close();
			socket.close();
			ss.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
