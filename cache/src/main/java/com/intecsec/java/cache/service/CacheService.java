package com.intecsec.java.cache.service;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-03-09 16:41
 **/
public interface CacheService {

    Object getFromMap(String key);
    void putMap(String key, Object value);
    void delMapCache(String key);

    Object getFromCaffeine(String key);
    void putCaffeine(String key, Object value);
    void delCaffeineCache(String key);

    Object getFromRedis(String key);
    String getStringFromRedis(String key);
    void putRedis(String key, Object value);
    void delRedis(String key);
}
