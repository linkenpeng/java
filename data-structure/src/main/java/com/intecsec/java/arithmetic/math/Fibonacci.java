package com.intecsec.java.arithmetic.math;

/**
 * @description: 斐波那契数列
 * @author: peter.peng
 * @create: 2021-02-01 22:42
 **/
public class Fibonacci {

    public static void main(String[] args) {

        int number = 8;

        System.out.println(forGen(number));
        System.out.println(recursion(number));

    }

    public static long forGen(int number) {

        if(number <= 0) {
            return 0;
        }

        if(number == 1 || number == 2) {
            return 1;
        }

        int first = 1, second = 1, third = 0;
        for(int i = 3; i <= number; i++) {
            third = first + second;
            first = second;
            second = third;
        }

        return third;
    }

    public static long recursion(int number) {
        if(number <= 0) {
            return 0;
        }

        if(number == 1 || number == 2) {
            return 1;
        }

        return recursion(number - 1) + recursion(number - 2);
    }
}
