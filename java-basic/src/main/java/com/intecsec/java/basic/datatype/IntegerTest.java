package com.intecsec.java.basic.datatype;

import java.math.BigDecimal;

/**
 * @description: 整型
 * @author: peter.peng
 * @create: 2019-02-01 10:43
 **/
public class IntegerTest {

    public static void main(String[] args) {
        BigDecimal bg = new BigDecimal(100L);
        System.out.println(bg.longValue());

        bg = new BigDecimal(50L);
        System.out.println(bg.longValue());

        int a = 100;
        int b = 3;
        int c = a / b ;
        System.out.println(c);

        System.out.println(hash(bg));
    }

    public static void bitCal() {
        System.out.println(-6 >>> 1);
        System.out.println(-6 >> 1);
        System.out.println(Integer.toBinaryString(-6));
        System.out.println(Integer.toBinaryString(-6 >>> 1));
        System.out.println(Integer.toBinaryString(-6 >> 1));
    }

    public static final int hash(Object key) {
        System.out.println("key hashCode:" + key.hashCode());
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public void compare() {
        Integer integer = 189;
        Integer integer2 = 200;
        Integer integer3 = 1;
        int integer4 = 1;
        System.out.println(integer > 0);
        System.out.println(integer2 > 0);
        System.out.println(integer3 > 0);
        System.out.println(integer4 > 0);
        System.out.println(integer > integer2);
    }
}
