package com.intecsec.java.basic.jmm;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @description:
 *  并发编程: 可见性、有序性、原子性
 *
 *  重排序遵循的原则：
 *  as-if-serial， 单线程执行结果不能被改变
 *  happens-before 某些代码必须发生在某些代码之前
 *
 * @author: peter.peng
 * @create: 2022-07-09 21:24
 **/
public class VolatileSerialTest {
    static int x = 0, y = 0;
    static int a = 0, b = 0;

    public static void main(String[] args) throws InterruptedException {
        Set<String> resultSet = new HashSet<>();

        ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        // TODO
        reentrantLock.unlock();

        reentrantLock.lock();
        // TODO
        reentrantLock.unlock();

        for (int i = 0; i < 1000000; i++) {
            x = 0;
            y = 0;
            a = 0;
            b = 0;
            Thread one = new Thread(() -> {
                a = y;
                x = 1;
            });
            Thread other = new Thread(() -> {
                b = x;
                y = 1;
            });
            one.start();
            other.start();
            // 合并进主线程
            one.join();
            other.join();

            resultSet.add("a=" + a + "," + "b=" +b);
            System.out.println(resultSet);
        }
    }
}
