package com.intecsec.java.springboot.thread;

import com.intecsec.java.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-11-18 11:42
 **/
@Component
@Slf4j
public class CompletableFutureParallel {

    @Resource
    private ThreadPoolExecutor completableFutureAsyncPool;

    public List<Integer> simpleParallel() {
        long t1 = System.currentTimeMillis();
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                int time = new Random().nextInt(3000);
                log.info("有返回值的异步任务:{} time:{}", Thread.currentThread().getName(), time);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return 1;
            }, completableFutureAsyncPool);

            futures.add(future);
        }
        long t2 = System.currentTimeMillis();
        log.info("初始化completableFuture 耗时:{}ms", (t2 - t1));

        long t3 = System.currentTimeMillis();

        // 拿到并行计算结果方法一，直接连接所有的CompletableFuture线程：
        List<Integer> resultList = futures.stream().map(CompletableFuture::join).collect(Collectors.toList());

        // 方法二：先使用allOf方法拿到所有线程的总集，然后取总集的结果
        /**
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(futures.stream().toArray(CompletableFuture[]::new));
        log.info("completableFuture allOf 耗时:{}ms", (t3 - t2));
        CompletableFuture<List<Integer>> finalResults = allFutures.thenApply(
                v -> futures.stream().map(CompletableFuture::join).collect(Collectors.toList())
        );
        List<Integer> resultList =  finalResults.join();
        */

        long t4 = System.currentTimeMillis();
        log.info("completableFuture join 耗时:{}ms 总耗时:{}",  (t4 - t3) , (t4 - t1));

        log.info("resultList:{}", resultList);

        return resultList;
    }
}
