package com.sjtu.HTTPLearningRelated;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.coyote.Response;
import org.mortbay.jetty.Request;

public class HttpServer {
	private boolean shutdown = false;
	public void await(){
		ServerSocket serverSocket = null;
		int port = 8080;
		try {
			serverSocket = new ServerSocket(port,1,InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (!shutdown){
			Socket socket = null;
			InputStream input = null;
			OutputStrea output = null;
			try {
				socket = serverSocket.accept();
				input = socket.getInputStream();
				output=socket.getOutputStream();
				Request request = new Request(input);
				request.parse();
				Response response = new Response(output);
				response.setRequest(request);
				response.sendStaticResource();
				soket.close();
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			} catch (Exception e){
				e.printStackTrace(s);
				continue;
			}
		}
	}
}
class Request {
	private String uri = null;
	public void parse(){
		StringBuffer request = new StringBuffer(2048);
		int i;
		byte[] buffer = new byte[2048];
		try {
			i = input.read(buffer);
		} catch (IOException e){
			e.printStackTrace();
			i = -1;
		}
		
		for (int j=0;j<i;j++){
			request.append((char) buffer[j]);
		}
		System.out.print(request.toString());
		uri = parseUri(request.toString());
	}
	private String parseUri(String requestString){
		int index1,index2;
		index1 = requestString.indexOf(' ');
		if (-1 != index1){
			index2 = requestString.indexOf(' ',index1 + 1);
			if (index2 > index1){
				return requestString.substring(index1+1, index2);
			}
		}
		return null;
	}
}
