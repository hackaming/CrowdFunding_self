package com.sjtu.IOLearning;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Demo1 {
	public static void main(String[] argv){
		File file = new File("d:/helloword.txt");
		InputStream in = null;
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			in = new FileInputStream(file);
			byte [] buf = new byte[1024];
			int length = 0;
			while((length=in.read(buf))!=-1){
				System.out.print(new String(buf,0,length));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1){
			e1.printStackTrace();
		} finally{
			if (null != in){
				try {
					in.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}
	}
}
