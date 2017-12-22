package com.sjtu.learning;

public class MultiThreadWait implements Runnable {
	private String name;
	private Object prev;
	private Object self;

	private MultiThreadWait(String name, Object prev, Object self) {
		this.name = name;
		this.prev = prev;
		this.self = self;
	}

	@Override
	public void run() {
		int count = 10;
		while (count > 0) {
			synchronized (prev) {
				synchronized (self) {
					System.out.print(name);
					count--;
					self.notify();
				}
				try {
					prev.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] argv) {
		Object a = new Object();
		Object b = new Object();
		Object c = new Object();
		MultiThreadWait pa = new MultiThreadWait("A", c, a);
		MultiThreadWait pb = new MultiThreadWait("B", a, b);
		MultiThreadWait pc = new MultiThreadWait("C", b, c);

		new Thread(pa).start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(pb).start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new Thread(pc).start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
