package com.intecsec.java.arithmetic.math;

/**
 * @description:
 *
给两个大整数, 用字符串表示, 比如" 2154365543",
"4332656442", 都可能超过1万位,
写一个函数输出他们之和. 需要自己实现加法过程,
不能用某些语言自带的高精度加法函数
 *
 * @author: peter.peng
 * @create: 2021-03-22 19:48
 **/
public class BigDataAdd {

    public static void main(String[] args) {

        String a = "7892";
        String b = "21111";

        // String a = "123";
        // String b = "123";

        System.out.println(add(a, b));
    }

    public static long add(String a ,String b) {
        a = new StringBuffer(a).reverse().toString();
        b = new StringBuffer(b).reverse().toString();

        int aLen = a.length();
        int bLen = b.length();

        int maxLen = aLen > bLen ? aLen : bLen;
        if(aLen - bLen > 0) {
            for (int i = 0; i < aLen - bLen; i++) {
                b += "0";
            }
        }
        if(bLen - aLen > 0) {
            for (int i = 0; i < bLen - aLen; i++) {
                a += "0";
            }
        }

        int iOver = 0;
        StringBuffer sum = new StringBuffer();
        for (int i = 0; i < maxLen; i++) {
            int i1 = Integer.parseInt(String.valueOf(a.charAt(i)));
            int i2 = Integer.parseInt(String.valueOf(b.charAt(i)));
            int temp = i1 + i2 + iOver;
            if(temp >= 10 ) {
                iOver = 1;
                int mod = temp % 10;
                sum.append(mod);
            } else {
                iOver = 0;
                sum.append(temp);
            }
        }

        if(iOver > 0) {
            sum.append(iOver);
        }

        return Long.valueOf(sum.reverse().toString());
    }

}
