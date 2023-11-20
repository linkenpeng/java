import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-11-20 21:32
 **/
public class CompletableTest {

    static ThreadPoolExecutor pool = new ThreadPoolExecutor(3,
            5, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(3));

    @Test
    public void fun02() throws ExecutionException, InterruptedException {

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                for (; ; ) {
                    int activeCount = pool.getActiveCount();
                    Thread.sleep(50);
                    System.out.println("activeCount = " + activeCount);
                }
            }
        }).start();
        for (int i = 0; i < 10; i++) {
            System.out.println("------");
            fun01();
            System.out.println("++++++");
        }
    }

    @Test
    public void fun01() throws ExecutionException, InterruptedException {
        long timeMillis = System.currentTimeMillis();
        CompletableFuture<Void> stringCompletableFuture1 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是1");
        }, pool);
        CompletableFuture<Void> stringCompletableFuture2 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是2");
        }, pool);
        CompletableFuture<Void> stringCompletableFuture3 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是3");
        }, pool);
        CompletableFuture<Void> stringCompletableFuture4 = CompletableFuture.runAsync(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("我是4");
        }, pool);

//        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(stringCompletableFuture1, stringCompletableFuture2, stringCompletableFuture3, stringCompletableFuture4);
//        objectCompletableFuture.join();

        //        stringCompletableFuture2.join();

//        Thread.sleep(4000);

//        stringCompletableFuture1.get();
//        stringCompletableFuture1.join();

//        Thread.sleep(3000);
//        Void now = stringCompletableFuture1.getNow(null);
//        System.out.println("now = " + now);


//        stringCompletableFuture1.complete()
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(stringCompletableFuture1,
                stringCompletableFuture2, stringCompletableFuture3, stringCompletableFuture4);
        voidCompletableFuture.join();
        long timeMillis2 = System.currentTimeMillis();
        System.out.println("(timeMillis2 - timeMillis) = " + (timeMillis2 - timeMillis));
    }
}
