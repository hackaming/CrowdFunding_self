package com.crowdfunding.sjtu.AQM;

import com.caucho.hessian.client.HessianProxyFactory;

public class Test {
	public void test1(){
		OrderServiceClient os = new OrderServiceClient("localhost");
		System.out.println(os.getUrl());
		os.calltest();
	}
	public static void main(String argv[]){
		new Test().test();
	}
	public void test(){
		String url = "http://localhost:8080/Crowdfunding/us"; 
		HessianProxyFactory factory = new HessianProxyFactory(); 
		try { 
		IHSTest hello =(IHSTest)factory.create(IHSTest.class,url); 
		String h = hello.hello();
		System.out.println(h);
		//System.out.println(hello.hello()); 
		} catch (Exception  e) { 
		e.printStackTrace(); 
		} 
	}
}
