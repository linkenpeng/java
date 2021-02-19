package com.intecsec.java.basic.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Peter.Peng
 * @date 2021/2/19
 */
public class ThreadPoolExecutorDemo {

	private static final int CORE_POOL_SIZE = 5;
	private static final int MAX_POOL_SIZE = 10;
	private static final int QUEUE_CAPACITY = 12;
	private static final long KEEP_ALIVE_TIME = 10L;

	public static void main(String[] args) {
		ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
				new ArrayBlockingQueue<>(QUEUE_CAPACITY), new ThreadPoolExecutor.CallerRunsPolicy());

		for (int i = 0; i < 20; i++) {
			Runnable worker = new MyRunnable("" + i);
			executor.execute(worker);
		}

		executor.shutdown();
		while (!executor.isTerminated()) {

		}

		System.out.println("Finished all threads");
	}

}
