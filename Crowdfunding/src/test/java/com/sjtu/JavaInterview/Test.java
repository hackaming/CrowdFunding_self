package com.sjtu.JavaInterview;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class Test {
	public static void main(String[] argv){
		byte[] arr = new byte[4];
		arr[0] = 0x78;
		arr[1] = 0x56;
		arr[2] = 0x34;
		arr[3] = 0x12;
		ByteArrayInputStream bais = new ByteArrayInputStream(arr);
		DataInputStream dis = new DataInputStream(bais);
		try {
			System.out.println(Integer.toHexString(dis.readInt()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
