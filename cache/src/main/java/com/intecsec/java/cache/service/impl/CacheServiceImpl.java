package com.intecsec.java.cache.service.impl;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.intecsec.java.cache.annotation.CacheTimeLog;
import com.intecsec.java.cache.localcache.MapCacheUtil;
import com.intecsec.java.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
            .refreshAfterWrite(10, TimeUnit.SECONDS)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .expireAfterAccess(10, TimeUnit.SECONDS)
            .maximumSize(10_000)
            .build(key -> new Date().toString());

    @Override
    public Object getFromRedis(String key) {
        Object value = redisTemplate.opsForValue().get(key);
        log.info("key:{}, value:{}", key, value);
        return value;
    }

    @Override
    public String getStringFromRedis(String key) {
        String value = null;
        try {
            value = (String) redisTemplate.opsForValue().get(key);
            log.info("key:{}, value:{}", key, value);
        } catch (Exception e) {
            log.info("error ", e);
        }
        return value;
    }

    @Override
    public Object getFromMap(String key) {
        return MapCacheUtil.getCache(key);
    }

    @Override
    public Object getFromCaffeine(String key) {
        return cache.getIfPresent(key);
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
