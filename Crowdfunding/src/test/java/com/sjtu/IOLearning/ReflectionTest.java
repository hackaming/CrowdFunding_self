package com.sjtu.IOLearning;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
/**
 * 
 * @author XYan
 * @since 2018/12/22
 */
public class ReflectionTest {
	public static void main(String[] argv){
		DisPlay display = new DisPlay();
		Class cls = display.getClass();
		try {
			Method method = cls.getMethod("show",String.class);
			method.invoke(display, "Yxm");
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
/**
 * 
 * @author XYan
 * @Function test class
 */
class DisPlay{
	public void show(String name){
		System.out.println("Hello:" + name);
	}
}