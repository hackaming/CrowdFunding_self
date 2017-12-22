package com.sjtu.learning;

public class TestThread2 {
	public static void main(String[] argv){
		new Thread(new Thread2("C")).start();
		new Thread(new Thread2("D")).start();
	}
}
