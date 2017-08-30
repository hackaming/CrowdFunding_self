package com.sjtu.WebSpider;
import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

public class First {
	private static Logger logger = Logger.getLogger("First");
	public static void main(String argv[]){
		String url = "https://www.taoguba.com.cn/index?blockID=1";
		First first = new First();
		
		String result = first.getPage(url);
		System.out.println(result);
		String strReg =  "a href=\"Article";
		String strTarget =  first.regString(strReg,result);
		System.out.println(strTarget);
	}

	public String getPage(String url){
		//String url = "http://www.baidu.com";
		String result = "";
		BufferedReader in = null;
		try{
			URL realUrl = new URL(url);
			//URLConnection connection = realUrl.openConnection();
			HttpsURLConnection connection = (HttpsURLConnection)realUrl.openConnection();
			
			//connection.connect();
			connection.setDoOutput(true);
			
/*			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			while((line=in.readLine())!=null){
				result += line;
			}
			*/
	        InputStreamReader insr = new InputStreamReader(connection.getInputStream());
	        // 读取服务器的响应内容并显示
	        int respInt = insr.read();
	        while (respInt != -1) {
	            System.out.print((char) respInt);
	            respInt = insr.read();
	        }
			
		} catch(Exception e){
			logger.info("发送GET请求出现异常！");
			e.printStackTrace();
		} finally{
			if (in !=null ){
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	public String regString(String reg,String string){
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(string);
		if (matcher.find()){
			return matcher.group(1);
		}
		return "";
	}
}
