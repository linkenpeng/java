package com.intecsec.java.springboot.thread;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-11-18 11:42
 **/
public class CompletableFutureParallel {

    @Resource
    private ThreadPoolExecutor ThreadPoolExecutor;

    public List<Integer> simpleParallel() {

        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
                System.out.println("有返回值的异步任务"+Thread.currentThread().getName());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return 1;
            }, ThreadPoolExecutor);

            futures.add(future);
        }

        // 使用allOf方法来表示所有的并行任务
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[futures.size()]));


        // 下面的方法可以帮助我们获得所有子任务的处理结果
        CompletableFuture<List<Integer>> finalResults = allFutures.thenApply(v ->
                futures.stream().map(CompletableFuture::join).collect(Collectors.toList())
        );
        List<Integer> resultList =  finalResults.join();

        System.out.println(resultList);

        return resultList;
    }
}
