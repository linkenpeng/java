package com.intecsec.java.cache.localcache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-03-09 16:56
 **/
public class MapCacheUtil {

    public static ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    public static String getCache(String key) {
        return cache.get(key);
    }

    public static void setCache(String key, String value) {
        cache.put(key, value);
    }

    public static void removeCache(String key) {
        cache.remove(key);
    }
}
