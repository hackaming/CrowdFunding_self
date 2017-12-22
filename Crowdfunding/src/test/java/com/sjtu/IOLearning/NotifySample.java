package com.sjtu.IOLearning;

public class NotifySample {
	public static void main(String[] args){
		NotifySample ns = new NotifySample();
		System.out.println("main thread starts");
		//ns.notifyTest();
		//ns.notifyTest2();
		ns.notifyTest3();
		System.out.println("main thread ends");
	}
	private static void notifyTest(){
		MyThread[] arrThreads = new MyThread[10];
		for (int i=0;i<arrThreads.length;i++){
			arrThreads[i] = new MyThread();
			arrThreads[i].id = i;
			arrThreads[i].setDaemon(true);
			arrThreads[i].start();
		}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i=0;i<arrThreads.length;i++){
			synchronized(arrThreads[i]){
				arrThreads[i].notify();
			}
		}
	}
	private static void notifyTest2(){
		MyRunner[] arrMyRunners = new MyRunner[100];
		Thread[] arrThreads = new Thread[100];
		for (int i=0;i<arrThreads.length;i++){
			arrMyRunners[i] = new MyRunner();
			arrMyRunners[i].id = i;
			arrThreads[i] = new Thread(arrMyRunners[i]);
			arrThreads[i].setDaemon(true);
			arrThreads[i].start();
		}
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i=0;i<arrMyRunners.length;i++){
			synchronized(arrMyRunners[i]){
				arrMyRunners[i].notify();
			}
		}
	}
	private static void notifyTest3(){
		MyRunner runner = new MyRunner();
		runner.id = 5 ;
		Thread[] arrThreads = new Thread[3];
		for (int i=0;i<arrThreads.length;i++){
			arrThreads[i] = new Thread(runner);
			arrThreads[i].setDaemon(true);
			arrThreads[i].start();
			
		}
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		synchronized(runner){
			runner.notifyAll();
		}
	}
}
class MyThread extends Thread{
	public int id = 0;
	public void run(){
		System.out.println("the "+id+" thread is about to sleep 5 minute.");
		synchronized(this){
			try {
				this.wait(5*60*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("the "+id+" thread is waken up");
	}
}
class MyRunner implements Runnable{
	public int id = 0;
	@Override
	public void run() {
		System.out.println("the "+id+" thread is about to sleep 5 minute.");
		synchronized(this){
			try {
				this.wait(5*60*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("the "+id+" thread is waken up!");
	}
}
