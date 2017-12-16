package com.sjtu.HttpServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpServer {
	private static final int port = 8086;
	private ServerSocket serverSocket = null;
	
	public HttpServer(){
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server's started at port:" + port);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Server started failure!Exception shows!");
			e.printStackTrace();
		}
	}
	public String getRequest(Socket socket){
		InputStream socketIn;
		byte[] requestBuff = null;
		try {
			socketIn = socket.getInputStream();
			int size = socketIn.available();
			requestBuff = new byte[size];
			socketIn.read(requestBuff);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new String(requestBuff);
	}
	public String getURI(String request){
		String firstLine = request.substring(0,request.indexOf("\r\n"));
		String[] parts = firstLine.split(" ");
		System.out.println("URI gets in getRequest and now return:"+parts);
	/*	for (int i = 0;i<parts.length;i++){
			System.out.println(parts[i]);
		}*/
		return parts[1];
	}
	public String getContentType(String URI){
		String contentType;
		if (URI.indexOf("html")!=-1 || URI.indexOf("htm")!=-1){
			contentType = "text/html";
		} else if (URI.indexOf("jpg")!=-1 || URI.indexOf("jpeg")!=-1){
			contentType = "image/jpeg";
		} else if (URI.indexOf("gif")!=-1){
			contentType = "image/gif";
		} else {
			contentType = "application/octet-stream";
		}
		return contentType;
	}
	public InputStream getResponseContent(String URI){
		InputStream htmlInputStream = null;
		try {
			htmlInputStream = new FileInputStream(System.getProperty("user.dir")+"/WebRoot"+URI);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Exception shows when get InputStream!");
			e.printStackTrace();
		}
		return htmlInputStream;
	}
	public String assembleResponseHead(String URI,String contentType){
		String responseFirstLine = "HTTP/1.1 200 OK\r\n";
		String responseHeader = "Content-Type:"+contentType+"\r\n\r\n";
		return responseFirstLine+responseHeader;
	}
	public void service(){
		while(true){
			Socket socket;
			try {
				socket = serverSocket.accept();
				//这里应该启一个线程;
				String request = getRequest(socket);
				System.out.println("HttpServer received request:\n" + request);
				String URI = getURI(request);
				String contentType = getContentType(URI);
				OutputStream out = socket.getOutputStream();
				out.write(assembleResponseHead(URI,contentType).getBytes());
				
				int len =0;
				byte[] buffer = new byte[128];
				InputStream htmlInputStream = getResponseContent(URI);
				while((len = htmlInputStream.read(buffer))!=-1){
					out.write(buffer,0,len);
				}
				Thread.sleep(1000);
				socket.close();
			} catch (Exception e){
				
			}
		}
	}
	public static void main(String[] argv){
		HttpServer server = new HttpServer();
		server.service();
	}
}
