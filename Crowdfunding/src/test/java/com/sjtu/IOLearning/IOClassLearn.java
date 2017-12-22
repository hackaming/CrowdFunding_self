package com.sjtu.IOLearning;
import java.io.*;
public class IOClassLearn {
	public static void main(String[] argv){
		IOClassLearn io = new IOClassLearn();
		FileReader fr = null;
		
		//io.FileInputStreamAndOutputStream();
		//io.inputStreamReaderAndWriter();
		io.bufferedReaderAndWriter();
	}
	public void fileInputStreamAndOutputStream(){ //byte
		File f = new File("d:/test.htm");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		if(!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fis = new FileInputStream(f);
			fos = new FileOutputStream("d:/mreoteng--FileInputStreamAndOutputStream.xml");
			int length = 0;
			while ((length = fis.read())!=-1){
				System.out.print((char)length);
				fos.write(length);
			}
			fos.flush();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1){
			e1.printStackTrace();
		} finally{
			if (null == fis){try{fis.close();}catch(IOException e2){e2.printStackTrace();}}
			if (null == fos){try{fos.close();}catch(IOException e3){e3.printStackTrace();}}
		}
		
	}
	public void bufferedReaderAndWriter(){
		BufferedReader br = null;
		BufferedWriter wr = null;
		File f = new File("d:/test.htm");
		if (!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			wr= new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d://test-BufferedReaderandWriter.htm")));
			String dd = null;
			while ((dd=br.readLine())!=null){
				wr.write(dd);
				//wr.newLine();
				wr.write("\t\r\n");
				System.out.println(dd);
			}
			wr.flush();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e1){
			e1.printStackTrace();
		} finally{
			if (null != br){try{br.close();}catch(IOException e3){e3.printStackTrace();}}
			if (null != wr){try{wr.flush();wr.close();}catch(IOException e4){e4.printStackTrace();}}
		}
	}
	public void inputStreamReaderAndWriter(){
		File f = new File("d:/test.htm");
		InputStreamReader fisr = null;
		OutputStreamWriter fosw = null;
		if (!f.exists()){
			try {
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			fisr = new InputStreamReader(new FileInputStream(f));
			fosw = new OutputStreamWriter(new FileOutputStream("d:/testInputStreamReaderWriter.htm"));
			int length =0;
			while ((length=fisr.read())!=-1){
				System.out.print((char)length);
				fosw.write(length);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}catch(IOException e2){
			e2.printStackTrace();
		}
		finally{
			if (null != fisr){try{fisr.close();}catch(IOException e3){e3.printStackTrace();}}
			if (null != fosw){try{fosw.close();}catch(IOException e4){e4.printStackTrace();}}
		}
		
	}
	

}
