package com.sjtu.learning;

public class ThreadYield extends Thread{
	public ThreadYield(String name){
		super(name);
	}
	public void run(){
		for (int i=1;i<50;i++){
			System.out.println(""+this.getName()+"----"+i);
			if (30 == i ){
				System.out.println("Now yield!"+this.getName());
				this.yield();
			}
		}
	}
}
