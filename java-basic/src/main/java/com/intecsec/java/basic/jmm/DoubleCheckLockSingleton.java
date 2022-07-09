package com.intecsec.java.basic.jmm;

/**
 * @description:
 * @author: peter.peng
 * @create: 2022-07-09 21:55
 **/
public class DoubleCheckLockSingleton {
    private static volatile DoubleCheckLockSingleton instance = null;

    private DoubleCheckLockSingleton() {

    }

    // DCL (double-checked-locking)
    public static DoubleCheckLockSingleton getInstance() {
        if(instance == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                if(instance == null) {
                    instance = new DoubleCheckLockSingleton();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        DoubleCheckLockSingleton instance = DoubleCheckLockSingleton.getInstance();
    }
}
