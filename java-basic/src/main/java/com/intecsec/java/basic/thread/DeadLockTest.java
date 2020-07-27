package com.intecsec.java.basic.thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @description: 线程死锁测试
 * @author: peter.peng
 * @create: 2019-06-24 22:04
 **/
public class DeadLockTest {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();
    // 这里的flag需要用volatile修饰，以保证线程间的可见性
    private static volatile boolean flag1 = false;
    private static volatile boolean flag2 = false;

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (lock1) {
                flag1 = true;
                System.out.println("thread1 acquired lock1");
                while (!flag2) {
                    // 无限循环，等待thread2获取到lock2后再继续往下执行（相比使用Thread.sleep(1000)在理论上是100%会出现死锁）
                    Thread.yield();
                }
                System.out.println("thread1 try to acquire lock2");
                synchronized (lock2) {
                    System.out.println("thread1 acquired lock2");
                }
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (lock2) {
                flag2 = true;
                System.out.println("thread2 acquired lock2");
                while (!flag1) {
                    Thread.yield();
                }
                System.out.println("thread2 try to acquire lock1");
                synchronized (lock1) {
                    System.out.println("thread2 acquired lock1");
                }
            }
        }, "t2").start();

        // 检测死锁
        checkDeadLock();
        System.out.println("main thread end");
    }

    public static void checkDeadLock() {
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(1);
        // 初始等待5秒，每隔10秒检测一次
        scheduled.scheduleAtFixedRate(() -> {
            long[] threadIds = mxBean.findDeadlockedThreads();
            if (threadIds != null) {
                System.out.println("检测到死锁线程：");
                ThreadInfo[] threadInfos = mxBean.getThreadInfo(threadIds);
                for (ThreadInfo info : threadInfos) {
                    System.out.println(info.getThreadId() + "：" + info.getThreadName());
                }
            }
        }, 5L, 10L, TimeUnit.SECONDS);
    }

}
