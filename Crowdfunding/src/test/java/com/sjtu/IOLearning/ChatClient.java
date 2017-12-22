package com.sjtu.IOLearning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatClient {
	public static void main(String[] argv){
		Socket socket = null;
		try {
			socket = new Socket("localhost",8889);
			BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter os = new PrintWriter(socket.getOutputStream());
			String line = sin.readLine();
			while (!line.toLowerCase().equals("exit!")){
				os.write(line);
				os.flush();
				System.out.println("Client says:"+line);
				System.out.println("Server says:"+br.readLine());
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
