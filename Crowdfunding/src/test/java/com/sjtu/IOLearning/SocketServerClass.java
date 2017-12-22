package com.sjtu.IOLearning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SocketServerClass {
	public static void main(String[] argv) {
		ServerSocket ss = null;
		List<ChatThread> clients = new ArrayList<ChatThread>();
		Boolean started = true;
		try {
			ss = new ServerSocket(8999);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println("Server's started!");
		while (started) {
			try {
				Socket socket = ss.accept();
				System.out.println("Cew client connected!");
				InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream();
				// ChatThread client = new ChatThread();
				ChatThread c = new ChatThread(in, out);
				clients.add(c);
				new Thread(c).start();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				
			}
		}
	}
}

class ChatThread implements Runnable {
	private InputStream in = null;
	private OutputStream out = null;

	public ChatThread() {

	}

	public ChatThread(InputStream in, OutputStream out) {
		this.in = in;
		this.out = out;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(out));
		DataInputStream dis = new DataInputStream(in);
		String line=null;
		do {
			try {
				line = dis.readLine();
				System.out.println("Client says:" + line);
				wr.write("Thanks,Server received your message:" + line);
				wr.flush();
				line = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (null != br) {
					try {
						br.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
				if (null != wr) {
					try {
						br.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
				if (null != in) {
					try {
						in.close();
					} catch (IOException e3) {
						e3.printStackTrace();
					}
				}
				if (null != in) {
					try {
						out.close();
					} catch (IOException e4) {
						e4.printStackTrace();
					}
				}
			}
		}while (!line.toLowerCase().equals("exit")) ;
	}
}
