package com.sjtu.IOLearning;

import java.lang.reflect.Field;

public class ReflectionTest1 {
	public static void main(String[] argv){
		Student student = new Student();
		student.setStuName("Xianming");
		student.setStuAge(35);
		
		Student destStudent = new Student();
		copyBean(student,destStudent);
		System.out.println(student.getStuName()+":"+destStudent.getStuName());		
	}
	public static void copyBean(Object from,Object dest){
		Class fromClass = from.getClass();
		Field[] fromFields = fromClass.getDeclaredFields();
		Class destClass = dest.getClass();
		Field destField = null;
		for (Field f:fromFields){
			String name = f.getName();
			try {
				destField = destClass.getDeclaredField(name);
				f.setAccessible(true);
				destField.setAccessible(true);
				try {
					destField.set(dest, f.get(from));
				} catch (IllegalArgumentException | IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
class Student{
	private String stuName;
	private int stuAge;
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public int getStuAge() {
		return stuAge;
	}
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
}
