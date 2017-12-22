package com.sjtu.learning;

public class MultiThreadJoin {
	public static void main(String[] argv){
		System.out.println(Thread.currentThread().getName()+"Main thread is running!");
		Thread1 mTh1 = new Thread1("A");
		Thread1 mTh2 = new Thread1("B");
		mTh1.start();mTh2.start();
		try {
			mTh1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mTh2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+"Main thread ended!");
	}
}
