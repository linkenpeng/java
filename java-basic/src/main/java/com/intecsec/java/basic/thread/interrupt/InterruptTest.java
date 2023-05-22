package com.intecsec.java.basic.thread.interrupt;

public class InterruptTest {

	public static void main(String[] args) throws InterruptedException {
		TestThread1 t1 = new TestThread1();
		TestThread2 t2 = new TestThread2();

		t1.start();
		t2.start();

		// 让线程运行一会儿后中断
		Thread.sleep(2000);
		t1.interrupt();
		t2.flag = false;
		System.out.println("main thread is exiting");
	}

}

class TestThread1 extends Thread {
	public void run() {
		// 判断标志，当本线程被别人interrupt后，JVM会被本线程设置interrupted标记
		while (!interrupted()) {
			System.out.println("test thread1 is running");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		}
		System.out.println("test thread1 is exiting");
	}
}

class TestThread2 extends Thread {
	public volatile boolean flag = true;
	public void run() {
		// 判断标志，当本线程被别人interrupt后，JVM会被本线程设置interrupted标记
		while (flag) {
			System.out.println("test thread2 is running");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("test thread2 is exiting");
	}
}
