package com.intecsec.java.cache.service;

import java.util.List;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-03-09 16:41
 **/
public interface LocalCacheService {

    String get(String key);

    List<String> getList(String key);

    void delMapCache(String key);

    void delGuavaCache(String key);
}
