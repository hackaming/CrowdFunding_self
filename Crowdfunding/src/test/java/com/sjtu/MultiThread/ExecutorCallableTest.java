package com.sjtu.MultiThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorCallableTest {
	static class Runner implements Callable<String>{
		private String runId;
		public Runner(String runId){
			this.runId = runId;
		}
		@Override
		public String call() throws Exception {
			System.out.println(Thread.currentThread().getName()+" call method is invoked!");
			return Thread.currentThread().getName() + " call method and id is " + runId;
		}
	}
	public static void main(String[] args){
		ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
		List<Future<String>> futureTaskList = new ArrayList<Future<String>>();
		for (int i=0;i<100;i++){
			Future<String> future = cachedThreadPool.submit(new Runner(String.valueOf(i)));
			futureTaskList.add(future);
		}
		for (Future f : futureTaskList){
			try{
				while(!f.isDone()){
					System.out.println(f.get()+"end!");
				}
			} catch (InterruptedException e){
				e.printStackTrace();
			} catch (ExecutionException e1){
				e1.printStackTrace();
			} finally {
				cachedThreadPool.shutdown();
			}
		}
	}
}
