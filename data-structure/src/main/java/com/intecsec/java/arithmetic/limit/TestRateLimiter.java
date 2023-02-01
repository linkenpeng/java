package com.intecsec.java.arithmetic.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @description: 令牌桶限流算法
 * @author: peter.peng
 * @create: 2021-03-06 11:55
 **/
public class TestRateLimiter {

    public static void main(String[] args) {
        final RateLimiter rateLimiter = RateLimiter.create(5);

        for (int i = 0; i < 10; i++) {
            long timeOut = (long) 0.5;
            boolean isValid = rateLimiter.tryAcquire(timeOut, TimeUnit.SECONDS);
            System.out.println("任务" + i + "执行是否有效:" + isValid);
            if (!isValid) {
                continue;
            }
            System.out.println("任务" + i + "在执行");
        }
        System.out.println("结束");
    }
}
