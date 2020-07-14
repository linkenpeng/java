package com.intecsec.java.basic.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.function.*;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-09-06 18:36
 **/
public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
       getList();
    }

    public static void test1() {
        String result = CompletableFuture.supplyAsync(()-> "Hello ").thenApplyAsync(v -> v + "world").join();
        System.out.println(result);
    }

    public static void test2() {
        CompletableFuture.supplyAsync(()-> "Hello ").thenAccept(v -> System.out.println("consumer: " + v));
    }

    /**
     * 无返回值
     */
    public static void runAsync() throws Exception {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
        });

        future.get();
    }

    /**
     * 有返回值
     */
    public static void supplyAsync() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
            }
            System.out.println("run end ...");
            return System.currentTimeMillis();
        });

        long time = future.get();
        System.out.println("time = "+time);
    }

    /**
     * 当一个线程依赖另一个线程时，可以使用 thenApply 方法来把这两个线程串行化。
     * @throws Exception
     */
    private static void thenApply() throws Exception {
        CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
            long result = new Random().nextInt(100);
            System.out.println("result1="+result);
            return result;
        }).thenApply(t -> {
            long result = t*5;
            System.out.println("result2="+result);
            return result;
        });

        long result = future.get();
        System.out.println(result);
    }

    /**
     * handle 是执行任务完成时对结果的处理。
     * handle 方法和 thenApply 方法处理方式基本一样。
     * 不同的是 handle 是在任务完成后再执行，还可以处理异常的任务。
     * thenApply 只可以执行正常的任务，任务出现异常则不执行 thenApply 方法。
     * @throws Exception
     */
    public static void handle() throws Exception {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            int i= 10/0;
            return new Random().nextInt(10);
        }).handle((param, throwable) -> {
            int result = -1;
            if(throwable==null) {
                result = param * 2;
            } else {
                System.out.println(throwable.getMessage());
            }
            return result;
        });

        System.out.println(future.get());
    }

    /**
     * 接收任务的处理结果，并消费处理，无返回结果。
     * @throws Exception
     */
    public static void thenAccept() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                .thenAccept(integer -> System.out.println(integer));
        future.get();
    }

    /**
     * 跟 thenAccept 方法不一样的是，不关心任务的处理结果。只要上面的任务执行完成，就开始执行 thenRun 。
     */
    public static void thenRun() throws Exception{
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> new Random().nextInt(10))
                .thenRun(() -> System.out.println("thenRun ..."));
        future.get();
    }

    /**
     * thenCombine 会把 两个 CompletionStage 的任务都执行完成后，把两个任务的结果一块交给 thenCombine 来处理。
     * @throws Exception
     */
    private static void thenCombine() throws Exception {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> "hello1");
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> "hello2");
        CompletableFuture<String> result = future1.thenCombine(future2, (t, u) -> t +" "+u);
        System.out.println(result.get());
    }

    /**
     * 当两个CompletionStage都执行完成后，把结果一块交给thenAcceptBoth来进行消耗
     * @throws Exception
     */
    private static void thenAcceptBoth() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> getT1());
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> getT2());
        f1.thenAcceptBoth(f2, (t, u) -> System.out.println("f1="+t + ";f2=" + u + ";"));
    }

    private static void thenAcceptAsyn() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> getT1());
        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> getT2());

        System.out.println("none 1");
        f1.thenAcceptAsync(t -> System.out.println(t));
        System.out.println("none 2");

        f2.thenAcceptAsync(t -> System.out.println(t));
        System.out.println("none 3");

        System.out.println(f1.get());
        System.out.println(f2.get());
    }

    private static void getList() throws Exception {
        List<Integer> iList = new ArrayList<>();
        List<CompletableFuture<Integer>> fs = new ArrayList<>();

        long t1 = System.currentTimeMillis();
        System.out.println("start: " + t1);
        for (int i = 0; i < 10; i++) {
            CompletableFuture<Integer> f = genFuture(i);
            fs.add(f);
        }

        int j = 0;
        for(CompletableFuture<Integer> f : fs) {
            System.out.println("j" + j + " " + f.get());
            j++;
        }

        long t2 = System.currentTimeMillis();
        System.out.println("end: " + (t2 - t1));


        for (int i = 0; i < 10; i++) {
            getT(i);
        }
        long t3 = System.currentTimeMillis();
        System.out.println("end: " + (t3 - t2));
    }

    private static CompletableFuture<Integer> genFuture(int i) {
        return CompletableFuture.supplyAsync(() -> getT(i));
    }

    /**
     * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的转化操作。
     */
    private static void applyToEither() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> getT1());

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> getT2());

        CompletableFuture<Integer> result = f1.applyToEither(f2, t -> {
            System.out.println(t);
            return t * 2;
        });

        System.out.println(result.get());
    }

    /**
     * 两个CompletionStage，谁执行返回的结果快，我就用那个CompletionStage的结果进行下一步的消耗操作。
     * @throws Exception
     */
    private static void acceptEither() throws Exception {

        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> getT1());

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> getT2());

        f1.acceptEither(f2, t -> System.out.println(t));
    }

    /**
     * 两个CompletionStage，任何一个完成了都会执行下一步的操作（Runnable）
     * @throws Exception
     */
    private static void runAfterEither() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> getT1());

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> getT2());

        f1.runAfterEither(f2, () -> System.out.println("上面有一个已经完成了。"));
    }

    /**
     * 两个CompletionStage，都完成了计算才会执行下一步的操作（Runnable）
     * @throws Exception
     */
    private static void runAfterBoth() throws Exception {
        CompletableFuture<Integer> f1 = CompletableFuture.supplyAsync(() -> getT1());

        CompletableFuture<Integer> f2 = CompletableFuture.supplyAsync(() -> getT2());

        f1.runAfterBoth(f2, () -> System.out.println("上面两个任务都执行完成了。"));
    }

    /**
     * thenCompose 方法允许你对两个 CompletionStage 进行流水线操作，第一个操作完成时，将其结果作为参数传递给第二个操作。
     * @throws Exception
     */
    private static void thenCompose() throws Exception {
        CompletableFuture<Integer> f = CompletableFuture.supplyAsync(() -> {
            int t = new Random().nextInt(3);
            System.out.println("t1="+t);
            return t;
        }).thenCompose(param -> CompletableFuture.supplyAsync(() -> {
            int t = param *2;
            System.out.println("t2="+t);
            return t;
        }));
        System.out.println("thenCompose result : "+f.get());
    }

    private static int getT1() {
        int t = new Random().nextInt(3);
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // System.out.println("f1="+t);
        return t;
    }

    private static int getT(int i) {
        int t = new Random().nextInt(3);
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(i + " -> f1=" + t);
        return t;
    }

    private static int getT2() {
        int t = new Random().nextInt(3);
        try {
            TimeUnit.SECONDS.sleep(t);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("f2="+t);
        return t;
    }

}
