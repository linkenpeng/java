package com.intecsec.java.basic.jvm;

import java.util.HashSet;

/**
 * @description: Test the Arthas
 *
 * java -jar arthas-boot.jar
 * dashboard [查看大盘]
 * thread thread num [查看cpu高的线程]
 * thread -b [查看死锁线程]
 * jad class file [反编译代码]
 *
 * @author: peter.peng
 * @create: 2022-07-03 22:48
 **/
public class ArthasTest {

    private static HashSet hashSet = new HashSet<>();

    public static void main(String[] args) {
        cpuHigh();
        deadThread();
        addHashSetThread();
    }

    public static void cpuHigh() {
        new Thread(() -> {
                while (true) {

                }
            }
        ).start();
    }


    public static void deadThread() {
        Object resourceA = new Object();
        Object resourceB = new Object();
        Thread threadA = new Thread(() -> {
            synchronized (resourceA) {
                System.out.println(Thread.currentThread() + " get resourceA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resourceB");
                synchronized (resourceB) {
                    System.out.println(Thread.currentThread() + " get resourceB");
                }
            }
        });
        Thread threadB = new Thread(() -> {
            synchronized (resourceB) {
                System.out.println(Thread.currentThread() + " get resourceB");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "waiting get resourceA");
                synchronized (resourceA) {
                    System.out.println(Thread.currentThread() + " get resourceA");
                }
            }
        });

        threadA.start();
        threadB.start();
    }

    public static void addHashSetThread() {
        new Thread(() -> {
            int count = 0;
            while (true) {
                try {
                    hashSet.add("count" + count);
                    Thread.sleep(10000);
                    count++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
