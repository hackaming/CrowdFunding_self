package com.sjtu.IOLearning;

public class MTThread {
	public static void main(String[] argv){
		   My1Runner runner = new My1Runner();
		    Thread thread1 = new Thread(runner);
		    Thread thread2 = new Thread(runner);
		    thread1.setDaemon(true);
		    thread2.setDaemon(true);
		    thread1.start();
		    thread2.start();
		    try {
				thread1.join();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    try {
				thread2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
class My1Runner implements Runnable
{
    public int sum = 0;
    
    public void run() 
    {
    	synchronized(this){
        System.out.println(Thread.currentThread().getName() + " Start.");
        for (int i = 1; i <= 100; i++)
        {
            sum += i;
        }
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " --- The value of sum is " + sum);
        System.out.println(Thread.currentThread().getName() + " End.");
    }
    }
}
