package com.sjtu.IOLearning;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SocketClientClass {
	public static void main(String[] argv){
		InputStream in = null;
		OutputStream out = null;
		Socket s = null;
		try {
			s = new Socket("localhost",8999);
			in = s.getInputStream();
			out = s.getOutputStream();
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(out));
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			Scanner scaner = new Scanner(System.in);
			String str = scaner.nextLine();
			while (str.toLowerCase().equals("exit")){
				bw.write(str);
				bw.flush();
				System.out.println(br.readLine());
				str = scaner.nextLine();
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (null != in){try{in.close();}catch(IOException e1){e1.printStackTrace();}}
			if (null != out){try{out.close();}catch(IOException e2){e2.printStackTrace();}}
		}
	}
}
