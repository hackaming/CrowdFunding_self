package com.sjtu.IOLearning;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamReaderLearn {
	public static void inputStreamReader(String filename){
		try {
			BufferedReader br = new BufferedReader((new FileReader(filename)));
			String r = null;
			while ( (r= br.readLine())!=null){
				System.out.println(r);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1){
			e1.printStackTrace();
		} finally{
			
		}
	}
	public static void main(String[] argv){
		InputStreamReaderLearn isrl = new InputStreamReaderLearn();
		isrl.inputStreamReader("d:\\mreoteng.xml");
	}
}
