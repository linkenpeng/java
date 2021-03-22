package com.intecsec.java.arithmetic;

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

        String a = "2154365543";
        String b = "994332656442";

        // String a = "123";
        // String b = "123";

        System.out.println(Long.valueOf(a) + Long.valueOf(b));
        System.out.println(add(a, b));
    }

    public static long add(String a ,String b) {

        int aLen = a.length();
        int bLen = b.length();

        StringBuilder tempZero = new StringBuilder();
        int zeroNum = aLen > bLen ? aLen - bLen : bLen - aLen;
        if(zeroNum > 0) {
            for(int s = zeroNum; s > 0; s--) {
                tempZero.append("0");
            }
        }

        if(aLen > bLen) {
            b = tempZero.append(b).toString();
        } else {
            a = tempZero.append(a).toString();
        }

        // System.out.println(a);
        // System.out.println(b);


        int min = a.length();

        StringBuilder sum = new StringBuilder();
        int[] poss = new int[min];

        for(int i = min; i > 0; i--) {
            int i1 = Integer.valueOf(a.substring(i - 1, i));
            int i2 = Integer.valueOf(b.substring(i - 1, i));
            int temp = i1 + i2 + poss[i-1];

            if(temp > 10) {
                int j = temp / 10;
                poss[i-1] = 1;
                sum.append(String.valueOf(j));
            } else {
                sum.append(String.valueOf(temp));
            }

        }

        return Long.valueOf(sum.toString());
    }

}
