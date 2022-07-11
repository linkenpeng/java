package com.intecsec.java.basic.jmm.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: peter.peng
 * @create: 2022-07-11 22:48
 **/
public class A {

    private int num;

    AtomicInteger atomicInteger = new AtomicInteger();

    public  void increase() {
        // 等同方法加synchronized
        /*synchronized (this) {
            num++;
        }*/

        atomicInteger.incrementAndGet();

        // 手写实现
        /*while (true) {
            int oldValue = atomicInteger.get();
            int newValue = oldValue + 1;
            if (atomicInteger.compareAndSet(oldValue, newValue)) {
                break;
            }
        }*/
    }

    public int getNum() {
        // return num;
        return atomicInteger.get();
    }
}
