package com.sjtu.IOLearning;

public class JoinSample {
	public static void main(String[] args){
		joinTest();
	}
	
	private static void joinTest(){
		Thread thread = new Thread(){
			public void run(){
				for (int i=0;i<5;i++){
					System.out.println("Thread's running.");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		};
		thread.setDaemon(true);
		thread.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
/*		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println("Main thread is ended normally");
	}
}
