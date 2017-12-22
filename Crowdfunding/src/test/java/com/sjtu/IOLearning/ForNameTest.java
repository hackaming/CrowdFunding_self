package com.sjtu.IOLearning;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ForNameTest {
	public static void main(String[] argv){
		try {
			Class<?> cls = Class.forName(argv[0]);
			Method[] methods = cls.getDeclaredMethods();
			Field[] fields = cls.getDeclaredFields();
			System.out.println("Mehtod lists:");
			for (Method m : methods){
				System.out.print("name:"+m.getName()+"\t");
				System.out.print("return type:"+m.getReturnType()+"\t");
				System.out.println("parameter count:"+m.getParameterCount()+"\t");
			}
			for (Field f : fields){
				System.out.print("name:"+f.getName()+"\t");
				System.out.print("modifier:"+f.getModifiers()+"\t");
				System.out.print("accessible:"+f.isAccessible()+"\t");
			}
			for (Method m:methods){
				System.out.println(m);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
