package com.intecsec.java.cache.service.impl;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.intecsec.java.cache.localcache.MapCacheUtil;
import com.intecsec.java.cache.service.LocalCacheService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-03-09 16:43
 **/
@Service
@Slf4j
public class LocalCacheServiceImpl implements LocalCacheService {

    LoadingCache<String, String> cache = CacheBuilder.newBuilder()
            .maximumSize(10).expireAfterWrite(100, TimeUnit.SECONDS)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return s + System.currentTimeMillis();
                }
            });

    @Override
    public String get(String key) {
        if(StringUtils.isNotEmpty(MapCacheUtil.getCache(key))) {
            log.info("从local cache 获取内容");
            return MapCacheUtil.getCache(key);
        }

        try {
            log.info("guava cache:" + cache.get(key));
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        String value = key + System.currentTimeMillis();

        log.info("设置本地缓存, key:{}, value:{}", key, value);
        MapCacheUtil.setCache(key, value);

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
    public void delGuavaCache(String key) {
        log.info("del guava cache key:{}", key);
        cache.refresh(key);
    }
}
