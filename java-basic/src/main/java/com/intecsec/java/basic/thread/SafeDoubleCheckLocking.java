package com.intecsec.java.basic.thread;

/**
 * @description: 双重锁检查，采用volatile申明变量，防止重排序
 * @author: peter.peng
 * @create: 2020-01-08 15:43
 **/
public class SafeDoubleCheckLocking {

    private volatile static Instance instance;

    public static Instance getInstance() {
        if (instance == null) {
            synchronized (SafeDoubleCheckLocking.class) {
                if (instance == null) {
                    instance = new Instance();
                }
            }
        }
        return instance;
    }
}
