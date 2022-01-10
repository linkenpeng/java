package com.intecsec.java.arithmetic;

/**
 * @description:
 * @author: peter.peng
 * @create: 2022-01-10 11:11
 **/
public class BitOp {

    /**
     * 自己实现幂乘
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if(x == 0.0f) {
            return 0.0d;
        }
        long b = n;
        double res = 1.0;
        if(b < 0) {
            x = 1 / x;
            b = -b;
        }
        while(b > 0) {
            if((b & 1) == 1) {
                res *= x;
            }
            x *= x;
            b >>= 1;
        }
        return res;
    }
}
