package com.sjtu.IOLearning;

import java.io.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Demo4 {
	public static void main(String[] argv){
		File file = new File("d:/helloword4.txt");
		OutputStream out = null;
		DataInputStream dis = null;
		BufferedInputStream bis = null;
		InputStreamReader reader = null;
		reader = new InputStreamReader(System.in);
		try {
			out = new FileOutputStream(file);
			int len = 0;
			while ((len=reader.read())!=-1){
				out.write(len);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1){
			e1.printStackTrace();
		} finally{
			try {
				if (null != out){
					out.close();
				}
				if (null != reader){
					reader.close();
				}
			} catch (IOException e3){
				e3.printStackTrace();
			}
		}
	}
}
