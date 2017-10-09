package com.cpuusage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GetServerStatus{
	private ServerSocket ss= null;

	public static void main(String argv[]){
		
	}
	public void start(){
		try {
			ServerSocket ss = new ServerSocket(9999);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void serve(){
		while(true){
			try {
				Socket s = ss.accept();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private class Client implements Runnable{
		private Socket s;
		public Client(Socket s){
			this.s = s;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
