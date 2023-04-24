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
 * execute(Runnable) 这个方法接收一个Runnable实例，并且异步的执行。
 * <br />
 * submit(Runnable) submit(Runnable)和execute(Runnable)区别是前者可以返回一个Future对象，
 * 通过返回的Future对象，我们可以检查提交的任务是否执行完毕。
 * <br />
 * submit(Callable)和submit(Runnable)类似，也会返回一个Future对象，
 * 但是除此之外，submit(Callable)接收的是一个Callable的实现，
 * Callable接口中的call()方法有一个返回值，可以返回任务的执行结果，而Runnable接口中的run()方法是void的，没有返回值。
 * <br />
 * invokeAny(…)方法接收的是一个Callable的集合，执行这个方法不会返回Future，
 * 但是会返回所有Callable任务中其中一个任务的执行结果。这个方法也无法保证返回的是哪个任务的执行结果，
 * 反正是其中的某一个。
 * <br />
 * invokeAll(…)与 invokeAny(…)类似也是接收一个Callable集合，但是前者执行之后会返回一个Future的List，
 * 其中对应着每个Callable任务执行后的Future对象。
 * <br />
 * ExecutorService的关闭
 * 当我们使用完成ExecutorService之后应该关闭它，否则它里面的线程会一直处于运行状态。
 * 如果要关闭ExecutorService中执行的线程，我们可以调用ExecutorService.shutdown()方法。
 * 在调用shutdown()方法之后，ExecutorService不会立即关闭，但是它不再接收新的任务，
 * 直到当前所有线程执行完成才会关闭，所有在shutdown()执行之前提交的任务都会被执行。
 * <br />
 * 如果我们想立即关闭ExecutorService，我们可以调用ExecutorService.shutdownNow()方法。
 * 这个动作将跳过所有正在执行的任务和被提交还没有执行的任务。但是它并不对正在执行的任务做任何保证，
 * 有可能它们都会停止，也有可能执行完成。
 *
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
        /**
         * corePoolSize : CPU + 1
         * maxPoolSize : 2CPU
         * 压测比较合适
         */
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
