package com.intecsec.java.springboot.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-11-18 11:39
 **/
@Configuration
@EnableAsync
public class CompletableFutureThreadPool {

    int qvCorePoolSize = 10;
    int qvMaxPoolSize = 50;

    long qvKeepAliveSeconds = 3000;

    int qvQueueCapacity = 10;

    @Bean(name = "completableFutureAsyncPool")
    public ThreadPoolExecutor CompletableFutureAsyncPool() {
        return new ThreadPoolExecutor(
                //核心线程数
                qvCorePoolSize,
                //最大线程数
                qvMaxPoolSize,
                qvKeepAliveSeconds,
                TimeUnit.SECONDS,
                //队列大小
                new LinkedBlockingDeque<Runnable>(qvQueueCapacity),
                //定义线程名称
                new ThreadFactory() {
                    private final AtomicInteger mThreadNum = new AtomicInteger(1);
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "qvVerifyAsyncPool-" + mThreadNum.getAndIncrement());
                    }
                },
                //拒绝策略
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

}
