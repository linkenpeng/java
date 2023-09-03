package com.intecsec.java.cache.controller;

import com.intecsec.java.cache.service.LocalCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-03-09 17:45
 **/
@RestController
@RequestMapping("/local/cache")
public class LocalCacheController {

    @Autowired
    private LocalCacheService localCacheService;

    @GetMapping("/get")
    public String get(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        return localCacheService.get(key);
    }

    @GetMapping("/list")
    public List<String> list(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        return localCacheService.getList(key);
    }

    @GetMapping("/delMapCache")
    public String delMapCache(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        localCacheService.delMapCache(key);
        return key;
    }

    @GetMapping("/delCaffeineCache")
    public String delCaffeineCache(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        localCacheService.delCaffeineCache(key);
        return key;
    }
}
