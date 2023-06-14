package com.intecsec.java.basic.jvm.stack;

/**
 * @description: 栈内存溢出
 *
 * 线程私有，存储类中每个方法的内容
 *
 * vm args: -Xss160k
 *
 * Exception: java.lang.StackOverflowError
 *
 * @author: peter.peng
 * @create: 2019-11-24 19:44
 **/
public class StackSOF {

    private int stackLength = 1;

    public static void main(String[] args) throws Throwable {

        StackSOF stackSOF = new StackSOF();

        try {
            stackSOF.stackLeaK();
        } catch (Throwable e) {
            System.out.println("stack length:" + stackSOF.stackLength);
            throw e;
        }

    }

    public void stackLeaK() {
        stackLength++;
        stackLeaK();
    }

}
