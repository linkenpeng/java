package com.intecsec.java.cache.service.impl;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.intecsec.java.cache.localcache.MapCacheUtil;
import com.intecsec.java.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-03-09 16:43
 **/
@Service
@Slf4j
public class CacheServiceImpl implements CacheService {

    @Resource
    private RedisTemplate redisTemplate;

    LoadingCache<String, Object> cache = Caffeine.newBuilder()
            //创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存
            //.refreshAfterWrite(10, TimeUnit.SECONDS)
            //.expireAfterWrite(10, TimeUnit.SECONDS)
            //.expireAfterAccess(10, TimeUnit.SECONDS)
            .maximumSize(10_000)
            //根据key查询数据库里面的值
            .build(key -> new Date().toString());

    @Override
    public Object getFromMap(String key) {
        long startTime = System.nanoTime();
        Object result =  MapCacheUtil.getCache(key);
        log.info("从Map获取内容 key:{} time:{}", key, System.nanoTime() - startTime);
        return result;
    }

    @Override
    public Object getFromCaffeine(String key) {
        long startTime = System.nanoTime();
        Object result =  cache.getIfPresent(key);
        log.info("从Caffeine获取内容 key:{} time:{}", key, System.nanoTime() - startTime);
        return result;
    }

    @Override
    public Object getFromRedis(String key) {
        long startTime = System.nanoTime();
        Object result = redisTemplate.opsForValue().get(key);
        log.info("从Redis获取内容 key:{} time:{}", key, System.nanoTime() - startTime);
        return result;
    }

    @Override
    public void putMap(String key, Object value) {
        log.info("设置Map缓存, key:{}, value:{}", key, value);
        MapCacheUtil.setCache(key, value);
    }

    @Override
    public void putCaffeine(String key, Object value) {
        log.info("设置Caffeine缓存, key:{}, value:{}", key, value);
        cache.put(key, value);
    }

    @Override
    public void delMapCache(String key) {
        log.info("del map key:{}", key);
        MapCacheUtil.removeCache(key);
    }

    @Override
    public void delCaffeineCache(String key) {
        log.info("del Caffeine key:{}", key);
        cache.put(key, null);
    }

    @Override
    public void putRedis(String key, Object value) {
        log.info("设置Redis缓存, key:{}, value:{}", key, value);
        redisTemplate.opsForValue().set(key, System.currentTimeMillis() + "");
    }

    @Override
    public void delRedis(String key) {
        log.info("删除Redis内容 key:{}", key);
        redisTemplate.delete(key);
    }
}
