package com.sjtu.learning;

public class YieldTest {
	public static void main(String[] argv){
		ThreadYield yt1 = new ThreadYield("A");
		ThreadYield yt2 = new ThreadYield("B");
		yt1.start();
		yt2.start();
	}
}
