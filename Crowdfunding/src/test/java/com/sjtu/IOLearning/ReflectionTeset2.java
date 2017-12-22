package com.sjtu.IOLearning;

import java.lang.reflect.Field;

/**
 * 
 * @author XYan
 *
 */
public class ReflectionTeset2 {
	public static void main(String[] argv){
		Student student = new Student();
		student.setStuName("Xianming");
		student.setStuAge(35);
		Student destStudent = (Student) copyBean(student);
		System.out.println(student.getStuName()+":"+destStudent.getStuName());
	}
	public static Object copyBean(Object from){
		Class<?> fromcls = from.getClass();
		Field[] fromFields = fromcls.getDeclaredFields();
		Object ints = null;
		try {
			ints = fromcls.newInstance();
			for (Field f:fromFields){
				f.setAccessible(true);
				f.set(ints, f.get(from));
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ints;
	}
}
class Student1{
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