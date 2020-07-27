package com.intecsec.java.basic.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class MultiThread {

	public static void main(String[] args) {
		threadTest3();
	}

	public static void threadTest1() {
		MyThread myThread = new MyThread();
		//myThread.setDaemon(true);
		myThread.setPriority(Thread.MAX_PRIORITY);
		myThread.start();

		int index = 0;
		while (true) {
			if (index++ == 100) {
				break;
			}
			System.out.println("main:" + Thread.currentThread().getName());
		}
	}

	public static void threadTest2() {
		for(int i = 0; i < 100; i++) {
			new Thread(new RunThead()).start();
		}
	}

	public static void threadTest3() {
		FutureTask<String> futureTask = new FutureTask<>(new CallableThread());
		new Thread(futureTask).start();

		System.out.println("Thread main");

		try {
			System.out.println("执行结果:" + futureTask.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
}

// 方式1
class MyThread extends Thread {
	@Override
	public void run() {
		while (true) {
			System.out.println(getName());
		}
	}
}

/**
 * 方式2
 * 最常用
 */
class RunThead implements Runnable {
	@Override
	public void run() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("New Thread");
	}
}

// 方式3
class CallableThread implements Callable<String> {
	@Override
	public String call() throws Exception {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("New Callable Thread");
		return "Thread3 Result";
	}
}
