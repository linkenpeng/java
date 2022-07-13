package com.intecsec.java.basic.jmm.lock;

/**
 * @description:
 * @author: peter.peng
 * @create: 2022-07-11 22:47
 **/
public class LockTest {

    public static void main(String[] args) throws InterruptedException {
        IncrementTest incrementTest = new IncrementTest();

        long start = System.currentTimeMillis();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000000; i++) {
                incrementTest.increase();
            }
        });
        t1.start();

        for (int i = 0; i < 1000000; i++) {
            incrementTest.increase();
        }
        t1.join();

        long end = System.currentTimeMillis();
        System.out.println(String.format("%sms", end - start));

        System.out.println(incrementTest.getNum());
    }

}
