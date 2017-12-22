package com.sjtu.IOLearning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
	public static void main(String[] argv){
		ServerSocket ss = null;
		Socket socket = null;
		try {
			ss = new ServerSocket (8889);
			System.out.println("Server's Started");
			socket = ss.accept();
			System.out.println("Client's connected!");		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String line = null;
		try {
			BufferedReader ir = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			System.out.println("client says:"+ir.readLine());
			line = sin.readLine();
			while (!line.toLowerCase().equals("exit!")){
				os.write(line);
				os.flush();
				System.out.println("Server says:"+line);
				System.out.println("client says:"+ir.readLine());
				line = sin.readLine();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
