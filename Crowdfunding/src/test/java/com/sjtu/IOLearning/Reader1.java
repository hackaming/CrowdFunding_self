package com.sjtu.IOLearning;

import java.io.*;

public class Reader1 {
	public static void readerFilefromInputStreamReader(String filename){
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(filename));
			char[] cbuf = new char[1024];
			for (int i=0;i<1024;i++){
				System.out.print(cbuf[i]);
				System.out.print((int)cbuf[i]);
			}
			int i = 0;
			while((i=isr.read())!=-1){
				System.out.print((char)i);
			}
/*			;
			while((i=isr.read(cbuf))!=-1){
				System.out.print(cbuf);
			}*/
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1){
			e1.printStackTrace();
		}
	}
	public static void main(String[] argv){
		Reader1 r1 = new Reader1();
		r1.readerFilefromInputStreamReader("d:\\mreoteng.xml");
	}
}
