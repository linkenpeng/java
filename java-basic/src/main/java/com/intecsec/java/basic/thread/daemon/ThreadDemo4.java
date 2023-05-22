package com.intecsec.java.basic.thread.daemon;

public class ThreadDemo4
{
	public static void main(String args[]) throws InterruptedException
	{
		TestThread4 t = new TestThread4();
		// 设置为守护线程才会跟着main一起存亡
		// t.setDaemon(true);
		t.start();
		Thread.sleep(2000);
		System.out.println("main thread is exiting");
	}
}
 class TestThread4 extends Thread
{
	public void run() 
	{
		while(true)
		{
			System.out.println("TestThread4" + "is running");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
} 
