package NetworkRelated;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientMany {
	public static void main(String argv[]) {
		try {
			Socket s = new Socket("10.62.150.21", 9997);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
			//writerToBufferedWriter(bw);
			//String str = readline();
			bw.write("sdfsdfsdf");
			bw.flush();
			s.close();
			if (null != bw) {
				bw.close();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

		}
	}

	public void createConnection() {

	}

	public static void writerToBufferedWriter(BufferedWriter bw) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:/ProjectController.java")));
		String str;
		while ((str = br.readLine()) != null) {
			bw.write(str);
			bw.flush();
			System.out.println("Client:"+str);
		}
		br.close();
	}
	public static String readline() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:/ProjectController.java")));
		String str;
		str = br.readLine();
		
		//while ((str = br.readLine()) != null) {
			System.out.println("Client:"+str);
		//}
		br.close();
		return str;
	}
	
}
