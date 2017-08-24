package NetworkRelated;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketClientDemo {
	public static void main(String argv[]){
		try{
			Socket ss = new Socket("10.62.150.21",9999);
			OutputStream os = ss.getOutputStream();
			PrintWriter pw = new PrintWriter(os);
			
			pw.write("Send data to server!");
			
			pw.flush();

			ss.shutdownOutput();
			
			InputStream is = ss.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String str = null;
			while((str=br.readLine())!=null){
				System.out.println("Client:This is client, get data from server:" + str);
			}
			ss.shutdownInput();
			br.close();
			isr.close();
			is.close();
			pw.close();
			os.close();
			ss.close();			
		} catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
