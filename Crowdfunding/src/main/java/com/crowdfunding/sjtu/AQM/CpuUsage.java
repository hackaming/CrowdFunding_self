package com.crowdfunding.sjtu.AQM;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

public class CpuUsage {
    public float getCpuRatioForWindows() throws IOException {
  	  String procCmd = System.getenv("windir") + "\\system32\\wbem\\wmic.exe cpu get LoadPercentage /value";
  	  Process s = Runtime.getRuntime().exec(procCmd);
  	  return readCpu1(s);
    }
    public float readCpu1(final Process proc) throws IOException{
    	float cpuUsage = 0;
    	proc.getOutputStream().close(); 
        InputStreamReader ir = new InputStreamReader(proc.getInputStream()); 
        LineNumberReader input = new LineNumberReader(ir); 
        String line = input.readLine(); 
        while ((line = input.readLine()) != null){
      	  if (line.indexOf("ntage")>0){
      		  cpuUsage = Float.parseFloat(line.substring(line.indexOf("=")+1));
      	  }
        }
        return cpuUsage;
    }
/*    public static void main(String argv[]){
    	CpuUsage c = new CpuUsage();
    	try {
			System.out.println(c.getCpuRatioForWindows());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }*/
}
