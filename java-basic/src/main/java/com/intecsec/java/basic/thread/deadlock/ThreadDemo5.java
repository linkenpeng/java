package com.intecsec.java.basic.thread.deadlock;

import java.util.concurrent.TimeUnit;

public class ThreadDemo5
{
	public static Integer r1 = 1;
	public static Integer r2 = 2;
	public static void main(String args[]) throws InterruptedException
	{
		TestThread51 t1 = new TestThread51();
		t1.start();
		TestThread52 t2 = new TestThread52();
		t2.start();
	}
}

class TestThread51 extends Thread
{
	public void run() 
	{
		//先要r1,再要r2
		synchronized(ThreadDemo5.r1)
		{
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(ThreadDemo5.r2)
			{
				System.out.println("TestThread51 is running");
			}
		}
	}
} 
class TestThread52 extends Thread
{
	public void run() 
	{
		//先要r2,再要r1
		synchronized(ThreadDemo5.r2)
		{
			try {
				TimeUnit.SECONDS.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized(ThreadDemo5.r1)
			{
				System.out.println("TestThread52 is running");
			}
		}
	}
} 
