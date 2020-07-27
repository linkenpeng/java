package com.intecsec.java.basic.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 线程池测试
 * @author: peter.peng
 * @create: 2019-04-22 15:40
 **/
public class ThreadPool {
    public static void main(String[] args) {
        nagativeThread();
        threadFactory();
    }

    public static void nagativeThread() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            final int j = i;
            executorService.execute(() -> {
                System.out.println("aaa" + j);
            });
        }
        executorService.shutdown();
    }

    public static void threadFactory() {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("demo-poll-%d").build();
        ExecutorService executorService = new ThreadPoolExecutor(1, 200,
                1000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(20),
                threadFactory);

        for (int i = 0; i < 10; i++) {
            final int j = i;
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + j);
            });
        }

        executorService.shutdown();
    }

}
