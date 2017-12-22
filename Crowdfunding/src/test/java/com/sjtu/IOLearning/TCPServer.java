package com.sjtu.IOLearning;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] argv){
		try {
			ServerSocket ss = new ServerSocket(8999);
			System.out.println("Server's started!");
			Socket socket = ss.accept();
			System.out.println("Client's connected!");
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			dos.writeUTF("Welcome "+socket.getRemoteSocketAddress());
			System.out.println("Received:"+dis.readUTF());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
