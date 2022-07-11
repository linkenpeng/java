package com.intecsec.java.basic.jmm;

/**
 * @description:
 *
 * Java规范定义的内存屏障
 * LoadLoad
 * StoreStore
 * LoadStore
 * StoreLoad
 *
 * @author: peter.peng
 * @create: 2022-07-09 21:55
 **/
public class DoubleCheckLockSingleton {
    private static volatile DoubleCheckLockSingleton instance = null;

    private static Integer balance = 5000;

    private DoubleCheckLockSingleton() {

    }

    // DCL (double-checked-locking)
    public static DoubleCheckLockSingleton getInstance() {
        if(instance == null) {
            /** Show Bytecode whit Jclasslib
             *  monitorenter
             * 11 getstatic #2 <com/intecsec/java/basic/jmm/DoubleCheckLockSingleton.instance : Lcom/intecsec/java/basic/jmm/DoubleCheckLockSingleton;>
             * 14 ifnonnull 27 (+13)
             * 17 new #3 <com/intecsec/java/basic/jmm/DoubleCheckLockSingleton>
             * 20 dup
             *
             * 重排序了, 半初始化，加volatile关键字，阻止重排序
             * 21 invokespecial #4 <com/intecsec/java/basic/jmm/DoubleCheckLockSingleton.<init> : ()V>
             * 24 putstatic #2 <com/intecsec/java/basic/jmm/DoubleCheckLockSingleton.instance : Lcom/intecsec/java/basic/jmm/DoubleCheckLockSingleton;>

             * 27 aload_0
             * 28 monitorexit
             */
            synchronized (DoubleCheckLockSingleton.class) {
                if(instance == null) {
                    // 类加载信息检查 —> 是否已加载 —> 加载类 —> 分配内存 —> 初始化零值 —> 设置对象头 —> 执行init方法
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
