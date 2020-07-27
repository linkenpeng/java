package com.intecsec.java.basic.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @description: 线程锁
 *
 * 另外，还有ReentrantReadWriteLock和JDK 1.8中推出的StampedLock，
 * ReentrantReadWriteLock是对ReentrantLock的复杂扩展，能适合更加复杂的业务场景，它可以实现一个方法中读写分离的锁的机制，并发性更高。
 * 此外，StampedLock在Lock的基础上，实现了满足乐观锁和悲观锁等一些在读线程越来越多的业务场景，对吞吐量有巨大的改进。<br>

 * volatile是Java 语言提供了一种稍弱的同步机制，用来确保将变量的更新操作通知到其他线程，
 * 保证了新值能立即同步到主内存，以及每次使用前立即从主内存刷新。当把变量声明为volatile类型后，
 * 编译器与运行时都会注意到这个变量是共享的。需要注意的是，volatile只提供了内存可见性，没有提供原子性。
 *
 * @author: peter.peng
 * @create: 2019-04-22 15:29
 **/
public class TheadLock {
    private byte[] lock = new byte[1];

    public static void main(String[] args) {

    }


    // 写法一、修饰在方法上 作用在方法体上的话，即使获得了锁那么进入方法体内分配资源还是需要一定时间的。
    public synchronized void add1() {

    }

    // 写法二、修饰在代码块上
    public void add2() {

        synchronized (this) {

        }
    }

    // 写法三、指定一个小的对象值进行加锁 效率最高
    public void add3() {
        synchronized (lock) {

        }
    }
}


/**
 * ReentrantLock是接口Lock的一个具体实现类。当许多线程视图访问ReentrantLock保护的共享资源时，
 * JVM将花费较少的时间来调度线程，用更多的时间执行线程。
 */
class ReentTrantLockTest {

    private final ReentrantLock lock = new ReentrantLock();

    public void method1() {

        lock.lock();  // 获得锁

        try {
          // 方法体
        } finally {

            lock.unlock();

        }
    }
}

