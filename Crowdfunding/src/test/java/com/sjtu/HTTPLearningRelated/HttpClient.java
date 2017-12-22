package com.sjtu.HTTPLearningRelated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpClient {
	
	public static void main(String [] argv){
		Socket socket = null;
		try {
			socket = new Socket("127.0.0.1",8080);
			OutputStream os = socket.getOutputStream();
			boolean autoflush = true;
			PrintWriter out = new PrintWriter(socket.getOutputStream(),autoflush);
			BufferedReader brin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			out.println("GET /index.jsp HTTP/1.1");
			out.println("Host: localhost:8080");
			out.println("Connection: Close");
			
			boolean loop = true;
			StringBuffer sb = new StringBuffer(8096);
			
			while (loop){
				if (brin.ready()){
					int i=0;
					while (-1 != i){
						i = brin.read();
						sb.append((char)i);
					}
					loop = false;
				}
				Thread.currentThread().sleep(50);
			}
			
			System.out.println(sb.toString());
			socket.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InterruptedException e2){
			e2.printStackTrace();
		}
		
	}

}
