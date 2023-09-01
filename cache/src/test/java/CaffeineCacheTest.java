import com.github.benmanes.caffeine.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-08-27 15:37
 **/
@Slf4j
public class CaffeineCacheTest {
    private static int NUM = 0;

    @Test
    public void manualCaffeineCache() {
        System.out.println("test");
        Cache<Object, Object> cache = Caffeine.newBuilder()
                //初始数量
                .initialCapacity(10)
                //最大条数
                .maximumSize(10)
                //expireAfterWrite和expireAfterAccess同时存在时，以expireAfterWrite为准
                //最后一次写操作后经过指定时间过期
                .expireAfterWrite(1, TimeUnit.SECONDS)
                //最后一次读或写操作后经过指定时间过期
                .expireAfterAccess(1, TimeUnit.SECONDS)
                //监听缓存被移除
                .removalListener((key, val, removalCause) -> { })
                //记录命中
                .recordStats()
                .build();

        cache.put("1","张三");
        //张三
        System.out.println(cache.getIfPresent("1"));
        //存储的是默认值
        System.out.println(cache.get("2",o -> "默认值"));
    }

    @Test
    public void loadingCache() {
        LoadingCache<String, String> loadingCache = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存；refreshAfterWrite仅支持LoadingCache
                .refreshAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .maximumSize(10)
                //根据key查询数据库里面的值，这里是个lamba表达式
                .build(key -> new Date().toString());
    }

    @Test
    public void asyncCache() {
        AsyncLoadingCache<String, String> asyncLoadingCache = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔刷新缓存；仅支持LoadingCache
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                //根据key查询数据库里面的值
                .buildAsync(key -> {
                    Thread.sleep(1000);
                    return new Date().toString();
                });

        //异步缓存返回的是CompletableFuture
        CompletableFuture<String> future = asyncLoadingCache.get("1");
        future.thenAccept(System.out::println);
    }

    /**
     * 缓存大小淘汰
     */
    @Test
    public void maximumSizeTest() throws InterruptedException {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                //超过10个后会使用W-TinyLFU算法进行淘汰
                .maximumSize(10)
                .evictionListener((key, val, removalCause) -> {
                    log.info("淘汰缓存：key:{} val:{}", key, val);
                })
                .build();

        for (int i = 1; i < 20; i++) {
            cache.put(i, i);
        }
        Thread.sleep(500);//缓存淘汰是异步的

        // 打印还没被淘汰的缓存
        System.out.println(cache.asMap());
    }

    /**
     * 权重淘汰
     */
    @Test
    public void maximumWeightTest() throws InterruptedException {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                //限制总权重，若所有缓存的权重加起来>总权重就会淘汰权重小的缓存
                .maximumWeight(100)
                .weigher((Weigher<Integer, Integer>) (key, value) -> key)
                .evictionListener((key, val, removalCause) -> {
                    log.info("淘汰缓存：key:{} val:{}", key, val);
                })
                .build();

        //总权重其实是=所有缓存的权重加起来
        int maximumWeight = 0;
        for (int i = 1; i < 20; i++) {
            cache.put(i, i);
            maximumWeight += i;
        }
        System.out.println("总权重=" + maximumWeight);
        Thread.sleep(500);//缓存淘汰是异步的

        // 打印还没被淘汰的缓存
        System.out.println(cache.asMap());
    }


    /**
     * 访问后到期（每次访问都会重置时间，也就是说如果一直被访问就不会被淘汰）
     */
    @Test
    public void expireAfterAccessTest() throws InterruptedException {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterAccess(1, TimeUnit.SECONDS)
                //可以指定调度程序来及时删除过期缓存项，而不是等待Caffeine触发定期维护
                //若不设置scheduler，则缓存会在下一次调用get的时候才会被动删除
                .scheduler(Scheduler.systemScheduler())
                .evictionListener((key, val, removalCause) -> {
                    log.info("淘汰缓存：key:{} val:{}", key, val);

                })
                .build();
        cache.put(1, 2);
        System.out.println(cache.getIfPresent(1));
        Thread.sleep(3000);
        System.out.println(cache.getIfPresent(1));//null
    }

    /**
     * 写入后到期
     */
    @Test
    public void expireAfterWriteTest() throws InterruptedException {
        Cache<Integer, Integer> cache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.SECONDS)
                //可以指定调度程序来及时删除过期缓存项，而不是等待Caffeine触发定期维护
                //若不设置scheduler，则缓存会在下一次调用get的时候才会被动删除
                .scheduler(Scheduler.systemScheduler())
                .evictionListener((key, val, removalCause) -> {
                    log.info("淘汰缓存：key:{} val:{}", key, val);
                })
                .build();
        cache.put(1, 2);
        Thread.sleep(3000);
        System.out.println(cache.getIfPresent(1));//null
    }

    @Test
    public void refreshAfterWriteTest() throws InterruptedException {
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                //模拟获取数据，每次获取就自增1
                .build(integer -> ++NUM);

        //获取ID=1的值，由于缓存里还没有，所以会自动放入缓存
        System.out.println(cache.get(1));// 1

        // 延迟2秒后，理论上自动刷新缓存后取到的值是2
        // 但其实不是，值还是1，因为refreshAfterWrite并不是设置了n秒后重新获取就会自动刷新
        // 而是x秒后&&第二次调用getIfPresent的时候才会被动刷新
        Thread.sleep(2000);
        System.out.println(cache.getIfPresent(1));// 1

        //此时才会刷新缓存，而第一次拿到的还是旧值
        System.out.println(cache.getIfPresent(1));// 2
    }

    @Test
    public void statics() {
        LoadingCache<String, String> cache = Caffeine.newBuilder()
                //创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存；refreshAfterWrite仅支持LoadingCache
                .refreshAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterWrite(1, TimeUnit.SECONDS)
                .expireAfterAccess(1, TimeUnit.SECONDS)
                .maximumSize(10)
                //开启记录缓存命中率等信息
                .recordStats()
                //根据key查询数据库里面的值
                .build(key -> {
                    Thread.sleep(1000);
                    return new Date().toString();
                });


        cache.put("1", "win");
        cache.get("1");

        /*
         * hitCount :命中的次数
         * missCount:未命中次数
         * requestCount:请求次数
         * hitRate:命中率
         * missRate:丢失率
         * loadSuccessCount:成功加载新值的次数
         * loadExceptionCount:失败加载新值的次数
         * totalLoadCount:总条数
         * loadExceptionRate:失败加载新值的比率
         * totalLoadTime:全部加载时间
         * evictionCount:丢失的条数
         */
        System.out.println(cache.stats());
    }

}
