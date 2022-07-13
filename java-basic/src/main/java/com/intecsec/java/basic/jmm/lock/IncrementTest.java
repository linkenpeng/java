package com.intecsec.java.basic.jmm.lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @description:
 * @author: peter.peng
 * @create: 2022-07-11 22:48
 **/
public class IncrementTest {

    private long num;

    AtomicInteger atomicInteger = new AtomicInteger();

    // 分段CAS
    LongAdder longAdder = new LongAdder();

    public  void increase() {
        // 等同方法加synchronized
        /**
         锁：互斥锁，悲观锁，同步锁，重量级锁
        线程阻塞、上下文切换、操作系统线程调度

         锁优化：
         单线程访问：偏向锁
         多线程访问：锁升级
        synchronized (this) {
            num++;
        }
        */

        // atomicInteger.incrementAndGet();
        longAdder.increment();

        // 手写实现
        /**
        CAS：无锁，自旋锁，乐观锁，轻量级锁
         1. 原子性问题 底层是c++ native 方法，硬件级别有锁，再调用汇编：lock cmpxchgq 缓存行锁
         2. ABA问题，一个线程修改了数据又修改回去，杜绝此问题，可以加版本号
        while (true) {
            int oldValue = atomicInteger.get();
            int newValue = oldValue + 1;
            if (atomicInteger.compareAndSet(oldValue, newValue)) {
                break;
            }
        }
        */
    }

    public long getNum() {
        // return num;
        // return atomicInteger.get();
        return longAdder.longValue();
    }
}
