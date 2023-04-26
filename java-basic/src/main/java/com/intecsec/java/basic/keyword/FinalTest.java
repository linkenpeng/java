package com.intecsec.java.basic.keyword;

import java.util.Calendar;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-04-26 07:01
 **/
public class FinalTest {
    // static and final can switch
    // static 保证内存中只有一份 final 保证赋值后不能再被修改
    public static final String CONSTANTS = "abc";
    public final static int CONST_INT = 1;

    public static void main(String[] args) {
        string();
    }

    /**
     * 所有属性申明为final和private
     * 不提供setter
     * 类和方法都是final
     * 类中包含mutable对象，需要深拷贝
     *
     * 优点：
     * 线程安全
     * 并发读提高性能
     * 可以重复使用
     *
     * 缺点：
     * 制造垃圾浪费空间
      */
    public static void immutable() {
        String a = "abc";
        String b = a;
        System.out.println(b);
        a = "def";
        System.out.println(b);
        System.out.println(a);

        change(b);
        System.out.println(b);

        // 字符串内容比较用equals, 引用比较用==
    }

    public static void string() {
        int i = 50000;
        Calendar t1 = Calendar.getInstance();
        String a = new String();
        for(int j = 0; j < i; j++) {
            a = a + j +  ",";
        }
        Calendar t2 = Calendar.getInstance();
        System.out.println(t2.getTimeInMillis() - t1.getTimeInMillis());

        // 线程安全
        StringBuffer b = new StringBuffer();
        for(int j = 0; j < i; j++) {
            b.append(j);
            b.append(",");
        }
        Calendar t3 = Calendar.getInstance();
        System.out.println(t3.getTimeInMillis() - t2.getTimeInMillis());

        // 线程不安全
        StringBuilder c = new StringBuilder();
        for(int j = 0; j < i; j++) {
            c.append(j);
            c.append(",");
        }
        Calendar t4 = Calendar.getInstance();
        System.out.println(t4.getTimeInMillis() - t3.getTimeInMillis());
    }

    public static void change(String b) {
        b = "change";
    }

    public static void mutable() {

    }

    /**
     * 常量池编译期就已经确定的数据
     * 是一块特殊的内存
     * 相同的常量字符串只存储一份，节省内存，共享访问
     */
    public static void constants() {
        // -128 ~ 127
        Integer i = 127;
        // 0 ~ 127
        Character c = 127;
        // -128 ~ 127
        Byte b1 = 127;
        // -128 ~ 127
        Short s = 127;
        // -128 ~ 127
        Long l = 127L;
        // true false
        Boolean b = true;
        // Float Double 没有常量池

        // String 都是常量池 放在栈内存
        String st = "abc";
        String st1 = "abc";
        System.out.println(st == st1);

        // 放在堆内存
        String nst1 = new String("abc");
        String nst2 = new String("abc");
        System.out.println(nst1 == nst2);

        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        String s4 = "abcabc";
        String s5 = s1 + s2;
        String s6 = "abc" + "abc";
        String s7 = "abc" + new String("abc");

        System.out.println(s1 == s2); // true
        System.out.println(s1 == s3); // false
        System.out.println(s4 == s5); // false 因为s5中有变量
        System.out.println(s4 == s6); // true
        System.out.println(s4 == s7); // false 因为s7中有new
    }
}

interface FinalTest1 {
    String abc = "abc";
    void move();
}