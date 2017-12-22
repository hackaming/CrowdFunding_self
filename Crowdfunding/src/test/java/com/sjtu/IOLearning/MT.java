package com.sjtu.IOLearning;

public class MT {
	public static void main(String[] argv){
/*		System.out.println(Thread.currentThread().getName());
		System.out.println(Thread.currentThread().getThreadGroup().getName());
		System.out.println(Thread.currentThread().getPriority());
		System.out.println(Thread.currentThread().isAlive());
		System.out.println(Thread.currentThread().isDaemon());
		System.out.println(Thread.MAX_PRIORITY);*/
		//prioritytest();
		daemonTest();
	}
	public void outt(){
		
	}
	public static void prioritytest(){
		Thread th1 = new Thread("low"){
			public void run(){
				for (int i=0;i<5;i++){
					System.out.println("Thread1's running");
				}
			}
		};
		Thread th2 = new Thread("high"){
			public void run(){
				for (int i=0;i<5;i++){
					System.out.println("Thread 2 is running");
				}
			}
		};
		th1.setPriority(Thread.MIN_PRIORITY);
		th2.setPriority(Thread.MAX_PRIORITY);
		th1.start();
		th2.start();
	}
	
	public static void daemonTest(){
		Thread th1 = new Thread("daemon"){
			public void run(){
				System.out.println("sub thread starts!");
				Thread subThread = new Thread("Sub"){
					public void run(){
						for (int i=0;i<100;i++){
							System.out.println("Sub Thread Running"+i);
						}
					}
				};
				subThread.setDaemon(true);
				subThread.start();
				System.out.println("Main Thread end.");
			}
		};
		th1.start();
	}
}
