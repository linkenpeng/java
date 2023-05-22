package com.intecsec.java.basic.thread.demo;

public class ThreadDemo1
{
	public static void main(String [] args)
	{
		TestThread1 t=new TestThread1();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
	}
}
class TestThread1 implements Runnable
{
	private int tickets=100;
	public void run()
	{
		while(true)
		{
			if(tickets>0)
			{
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				tickets--;
				System.out.println(Thread.currentThread().getName() +" is selling ticket " + tickets);
			}
			else
			{
				break;
			}
				
		}
	}
}
