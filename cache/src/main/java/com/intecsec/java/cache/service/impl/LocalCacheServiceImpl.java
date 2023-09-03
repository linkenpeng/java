package com.intecsec.java.cache.service.impl;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.intecsec.java.cache.localcache.MapCacheUtil;
import com.intecsec.java.cache.service.LocalCacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-03-09 16:43
 **/
@Service
@Slf4j
public class LocalCacheServiceImpl implements LocalCacheService {
    LoadingCache<String, String> cache = Caffeine.newBuilder()
            //创建缓存或者最近一次更新缓存后经过指定时间间隔，刷新缓存
            .refreshAfterWrite(10, TimeUnit.SECONDS)
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .expireAfterAccess(10, TimeUnit.SECONDS)
            .maximumSize(10_000)
            //根据key查询数据库里面的值
            .build(key -> new Date().toString());

    @Override
    public String get(String key) {
        if(StringUtils.isNotEmpty(MapCacheUtil.getCache(key))) {
            log.info("从local cache 获取内容");
            return MapCacheUtil.getCache(key);
        }

        if(StringUtils.isNotEmpty(cache.get(key))) {
            log.info("从Caffeine cache 获取内容");
            return cache.get(key);
        }

        String value = key + System.currentTimeMillis();

        log.info("设置本地缓存, key:{}, value:{}", key, value);
        MapCacheUtil.setCache(key, value);

        log.info("设置Caffeine缓存, key:{}, value:{}", key, value);
        cache.put(key, value);

        return value;
    }

    @Override
    public List<String> getList(String key) {
        return Arrays.asList("a", "b", "c");
    }

    @Override
    public void delMapCache(String key) {
        log.info("del map cache key:{}", key);
        MapCacheUtil.removeCache(key);
    }

    @Override
    public void delCaffeineCache(String key) {
        log.info("del Caffeine cache key:{}", key);
        cache.refresh(key);
    }
}
