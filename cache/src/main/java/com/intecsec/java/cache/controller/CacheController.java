package com.intecsec.java.cache.controller;

import com.intecsec.java.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-03-09 17:45
 **/
@RestController
@RequestMapping("/cache")
@Slf4j
public class CacheController {

    @Resource
    private CacheService cacheService;


    @GetMapping("/map/get/")
    public Object mapGet(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        return cacheService.getFromMap(key);
    }

    @GetMapping("/map/del")
    public String mapDel(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        cacheService.delMapCache(key);
        return key;
    }

    @GetMapping("/map/put")
    public String mapPut(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        cacheService.putMap(key, System.currentTimeMillis() + "");
        return key;
    }

    @GetMapping("/redis/get/")
    public Object redisGet(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        return cacheService.getFromRedis(key);
    }

    @GetMapping("/redis/del")
    public String redisDel(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        cacheService.delRedis(key);
        return key;
    }

    @GetMapping("/redis/put")
    public String redisPut(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        cacheService.putRedis(key, System.currentTimeMillis() + "");
        return key;
    }

    @GetMapping("/caffeine/get")
    public Object caffeineGet(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        return cacheService.getFromCaffeine(key);
    }

    @GetMapping("/caffeine/del")
    public String caffeineDel(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        cacheService.delCaffeineCache(key);
        return key;
    }

    @GetMapping("/caffeine/put")
    public String caffeinePut(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        cacheService.putCaffeine(key, System.currentTimeMillis() + "");
        return key;
    }
}
