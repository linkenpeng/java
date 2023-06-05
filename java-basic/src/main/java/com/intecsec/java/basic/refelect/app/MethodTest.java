package com.intecsec.java.basic.refelect.app;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MethodTest {

	public static void main(String[] args) throws InterruptedException {
		Timer timer = new Timer();

		Calendar now = Calendar.getInstance();
		now.set(Calendar.SECOND, 
				now.get(Calendar.SECOND) + 1);
		Date runDate = now.getTime();

		MyTask task2 = new MyTask();
		timer.scheduleAtFixedRate(task2, runDate, 3000); 
		// 固定速率

		Thread.sleep(15000);
		timer.cancel(); // 取消定时器
	}
}

class MyTask extends TimerTask {
	public void run() {
		try {
			Method m = Class.forName("Worker")
					.getClass().getMethod("hello");
			m.invoke(null);// 静态方法可以不用new对象
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class Worker {
	public static void hello() {
		System.out.println("Hello java!");
	}
}
