package com.intecsec.java.cache.constans;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-08-27 22:45
 **/
public enum CacheEnum {
    GET_USER("GET:USER", 3600L),
    GET_DYNAMIC("GET:DYNAMIC", 3600L),
    ;
    private String name;
    private long expires;

    CacheEnum(String name, long expires) {
        this.name = name;
        this.expires = expires;
    }

    public String getName() {
        return name;
    }

    public long getExpires() {
        return expires;
    }
}
