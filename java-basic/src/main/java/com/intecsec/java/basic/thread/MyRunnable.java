package com.intecsec.java.basic.thread;

import java.util.Date;

/**
 * @author Peter.Peng
 * @date 2021/2/19
 */
public class MyRunnable implements Runnable {

	private String command;

	public MyRunnable(String command) {
		this.command = command;
	}

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + " Start. Time = " + new Date());
		processCommand();
		System.out.println(Thread.currentThread().getName() + " End. Time = " + new Date());
	}

	private void processCommand() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "MyRunnable{" +
				"command='" + command + '\'' +
				'}';
	}
}
