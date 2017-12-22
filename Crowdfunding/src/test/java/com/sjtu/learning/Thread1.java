package com.sjtu.learning;

public class Thread1 extends Thread{
	private String name;
	public Thread1(String name){
		this.name = name;
	}
	public void run(){
		for (int i=0;i<5;i++){
			System.out.println(name + " is running "+i);
			try {
				sleep((int) Math.random()*10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
