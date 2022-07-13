package com.intecsec.java.basic.jmm.lock;

import org.openjdk.jol.info.ClassLayout;

/**
 * @description:
 * @author: peter.peng
 * @create: 2022-07-12 22:43
 **/
public class LockUpgrade {

    public static void main(String[] args) throws Exception {
        User userTemp = new User();

        System.out.println("无锁状态(001):" + ClassLayout.parseInstance(userTemp).toPrintable());

        /**
         * jvm 默认延时4s开启偏向锁，可通过 -XX:BiasedLockingStartupDelay=0 取消延时；
         * 如果不要偏向锁，可通过 -XX:-UseBiasedLocking = false 来设置
         */
        Thread.sleep(5000);
        User user = new User();
        System.out.println("启用偏向锁（101）：" + ClassLayout.parseInstance(user).toPrintable());

        for (int i = 0; i < 2; i++) {
            synchronized (user) {
                System.out.println("偏向锁（101）（带线程id）：" + ClassLayout.parseInstance(user).toPrintable());
            }
            System.out.println("偏向锁释放（101）（带线程id)：" + ClassLayout.parseInstance(user).toPrintable());
        }

        new Thread(() -> {
            synchronized (user) {
                System.out.println("重量级锁（00）：" + ClassLayout.parseInstance(user).toPrintable());
                try {
                    System.out.println("睡眠3秒======");
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("轻量 --> 重量级锁（10）：" + ClassLayout.parseInstance(user).toPrintable());
            }
        }).start();

        Thread.sleep(1000);
        new Thread(() -> {
            // 大量C++实现，java字节码：monitor enter, monitor exit, C++调用了inflate
            synchronized (user) {
                System.out.println("重量级锁（10）：" + ClassLayout.parseInstance(user).toPrintable());
            }
        }).start();
    }

}
