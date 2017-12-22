package com.sjtu.IOLearning;

public class StopThreadSample {

    public static void main(String[] args) throws InterruptedException
    {
        stopTest();
    }
    
    private static void stopTest() throws InterruptedException
    {
        Thread thread = new Thread()
        {
            public void run()
            {
                System.out.println("线程运行中。");
                try
                {
                    Thread.sleep(1*60*1000);
                }
                catch(InterruptedException ex)
                {
                    System.out.println("线程中断");
                    //return;
                }
                System.out.println("线程正常结束。");
            }
        };
        thread.start();
        Thread.sleep(500);
        //thread.interrupt();
        thread.stop();
    }
}