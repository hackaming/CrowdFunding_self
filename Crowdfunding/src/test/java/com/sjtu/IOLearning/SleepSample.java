package com.sjtu.IOLearning;

public class SleepSample {
    
    public static void main(String[] args) throws InterruptedException
    {
        sleepTest();
    }
    
    private static void sleepTest() throws InterruptedException
    {
        Thread thread = new Thread()
        {
            public void run()
            {
                System.out.println("线程 " + Thread.currentThread().getName() + "将要休眠5分钟。");
                try
                {
                    Thread.sleep(5*60*1000);
                }
                catch(InterruptedException ex)
                {
                    System.out.println("线程 " + Thread.currentThread().getName() + "休眠被中断。");
                }
                System.out.println("线程 " + Thread.currentThread().getName() + "休眠结束。");
            }
        };
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }

}