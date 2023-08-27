package com.intecsec.java.cache.localcache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class CaffeineCacheUtil {

    private static LoadingCache<String, String> cache = Caffeine.newBuilder().initialCapacity(1)
            .maximumSize(100).expireAfterWrite(1, TimeUnit.DAYS)
            .build(s -> "default");

    public static void main(String[] args) {
        System.out.println(cache.get("default"));
    }
}
