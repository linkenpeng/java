package com.intecsec.java.basic.keyword;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-02-22 23:14
 **/
public class StaticTest {

    StaticTest() {
        System.out.print("构造函数-> ");
    }

    {
        System.out.print("非静态代码块-> ");
    }

    static {
        System.out.print("静态代码块-> ");
    }

    public static void test() {
        System.out.print("静态方法-> ");
    }

    public void noTest() {
        System.out.print("非静态方法-> ");
    }

    public static void main(String[] args) {
        StaticTest.test(); // 静态代码块-> 静态方法->

        // StaticTest staticTest = new StaticTest(); // 静态代码块-> 非静态代码块-> 构造函数->
        // staticTest.noTest();
    }
}
