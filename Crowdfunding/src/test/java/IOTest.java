import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class IOTest {
	public static void main(String argv[]) throws IOException{
		IOTest iotest = new IOTest();
		//iotest.testFileInputStream();
		//iotest.testFileReader();
		iotest.testBufferedReader();
	}
	public void testFile(){
		File file = new File("d:/java/ProjectController.java");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.list());
		System.out.println(file.getTotalSpace()/1024/1024/1024);
		System.out.println(file.getFreeSpace()/1024/1024/1024);
		System.out.println(file.toURI());
	}
	public void testFileInputStream() throws IOException{
		FileInputStream fis = new FileInputStream("d:/java/iotest.png");
		FileOutputStream fos = new FileOutputStream("d:/java/iotest-write.png");
		int c ;
		while ((c = fis.read()) != -1){
			System.out.print(c);
			fos.write(c);
		}
		fis.close();
		fos.close();
	}
	public void testFileReader() throws IOException{
		FileReader reader = new FileReader("d:/java/ProjectController.java");
		FileWriter writer = new FileWriter("d:/java/ProjectController-write.java");
		char[] buf = new char[24];
		int i = 0;
		while ((i=reader.read(buf)) != -1){
			System.out.print(buf);
			writer.write(buf,0,i); //需要以这种写法，要不然会出错buf最后一行可能会读完了，但把以前的也写进去
		}
		reader.close();
		writer.close();
	}
	public void testBufferedReader() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("d:/java/ProjectController.java")));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("d:/java/ProjectController-wr.java")));
		
		BufferedReader br1 = new BufferedReader(new FileReader("d:/java/ProjectController-write.java"));
		BufferedWriter bw1 = new BufferedWriter(new FileWriter("d:/java/ProjectController-write-bw.java"));
		String str;
		while((str = br.readLine())!=null){
			System.out.println(str);
			bw.write(str);
			bw.newLine();
			//add code to write into another file---copy
		}
		while((str = br1.readLine())!=null){
			System.out.println(str);
			bw1.write(str);
			bw1.newLine();
			//add code to write into another file--copy
		}
		br.close();
		bw.flush();
		bw.close();
		br1.close();
		bw1.flush();
		bw1.close();
	}
}
