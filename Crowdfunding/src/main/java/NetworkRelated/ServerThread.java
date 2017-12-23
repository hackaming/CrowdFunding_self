package NetworkRelated;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.Socket;

public class ServerThread extends Thread{
	private Socket s;
	public ServerThread(Socket s){
		this.s = s;
	}

	@Override
	public void run() {
		InputStream is = null;
		BufferedReader br = null;
		OutputStream os = null;
		BufferedWriter bw = null;
		try {
			br = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String str;
			while ((str=br.readLine())!=null && null!=br){
				System.out.println(str);
			}
			
			os = s.getOutputStream();
			bw = new BufferedWriter(new OutputStreamWriter(os));
			bw.write("Finishd!");
			bw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			if (null != br){
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	
			if (null != os){
				try {
					os.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (null != bw){
				try {
					bw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

}
