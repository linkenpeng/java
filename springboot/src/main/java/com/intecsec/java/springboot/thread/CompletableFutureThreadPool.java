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
    int corePoolSize = 10;
    int maxPoolSize = 50;
    long keepAliveSeconds = 3000;
    int queueCapacity = 10;

    @Bean(name = "completableFutureAsyncPool")
    public ThreadPoolExecutor CompletableFutureAsyncPool() {
        return new ThreadPoolExecutor(
                //核心线程数
                corePoolSize,
                //最大线程数
                maxPoolSize,
                keepAliveSeconds,
                TimeUnit.SECONDS,
                //队列大小
                new LinkedBlockingDeque<>(queueCapacity),
                //定义线程名称
                new ThreadFactory() {
                    private final AtomicInteger threadNum = new AtomicInteger(1);
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r, "AsyncPool-" + threadNum.getAndIncrement());
                    }
                },
                //拒绝策略
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

}
