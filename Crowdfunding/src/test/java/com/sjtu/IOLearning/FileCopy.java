package com.sjtu.IOLearning;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileCopy {
	public void filecopy(String source,String dest){
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(source);
			fos = new FileOutputStream(dest);
			int i = 0;
			while ((i=fis.read())!=-1){
				System.out.print((char)i);
				fos.write(i);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1){
			e1.printStackTrace();
		} finally {
			if (null != fis){ try{fis.close();}catch (IOException e2){e2.printStackTrace();}}
			if (null !=fos ){try {fos.close();} catch (IOException e3) {e3.printStackTrace();}}
		}
		try{
			fis = new FileInputStream(dest);
			int i=0;
			while((i=fis.read())!=-1){
				System.out.print((char)i);
			}
		}catch (FileNotFoundException e4){
			
		} catch (IOException e5){
			e5.printStackTrace();
		} finally{
			if (null!=fis){try{fis.close();}catch(IOException e6){e6.printStackTrace();}}
		}
	}
	public static void main(String[] argv){
		FileCopy fc = new FileCopy();
		fc.filecopy("d:\\mreoteng.xml", "d:\\mreoteng-copied.xml");
	}
}
