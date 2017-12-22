package com.sjtu.IOLearning;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class PrivateTest {
	private String helo = "hello";
	public long number;
	
	public String getHelo() {
		return helo;
	}

	public void setHelo(String helo) {
		this.helo = helo;
	}

	public long getNumber() {
		return number;
	}

	public void setNumber(long number) {
		this.number = number;
	}

	public static void main(String[] argv){
		PrivateTest pt = new PrivateTest();
		Class cls = PrivateTest.class;
		try {
			Field fild = cls.getDeclaredField("helo");
			fild.setAccessible(true);
			fild.set(pt, "NiHao");
			fild.setAccessible(false);
			System.out.println(pt.getHelo());
			
			fild.setAccessible(true);
			Field[] fields = cls.getDeclaredFields();
			for (int i=0;i<fields.length;i++){
				System.out.print(fields[i].getName()+"\t");
				System.out.print(fields[i].getModifiers()+"\t");
			}
			for(Field f :fields){
				System.out.print(f.getName()+"\t");
				System.out.print(f.getModifiers()+"\t");				
			}

		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalArgumentException e2){
			e2.printStackTrace();
		} catch (IllegalAccessException e3){
			e3.printStackTrace();
		}
	}
}
