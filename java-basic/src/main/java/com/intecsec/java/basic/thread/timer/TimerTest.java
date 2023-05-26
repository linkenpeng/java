package com.intecsec.java.basic.thread.timer;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTest {
	public static void main(String[] args) throws InterruptedException {
			MyTask task = new MyTask();
			Timer timer = new Timer();
			
			System.out.println("当前时间："+new Date().toLocaleString());
			//当前时间1秒后，每2秒执行一次
			timer.schedule(task, 1000, 2000);
			
			Thread.sleep(10000);
			task.cancel();  //取消当前的任务
			
			System.out.println("================================");
			
			Calendar now = Calendar.getInstance();
			now.set(Calendar.SECOND,now.get(Calendar.SECOND)+3);
	        Date runDate = now.getTime();
	        MyTask2 task2 = new MyTask2();
	        timer.scheduleAtFixedRate(task2,runDate,3000); //固定速率
	        
			
	        Thread.sleep(20000);
			timer.cancel();  //取消定时器
	}
}

class MyTask extends TimerTask {
	public void run() {
		System.out.println("运行了！时间为：" + new Date());
	}
}

class MyTask2 extends TimerTask {
	public void run() {
		System.out.println("运行了！时间为：" + new Date());
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
