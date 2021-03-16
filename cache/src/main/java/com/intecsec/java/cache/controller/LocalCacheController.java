package com.intecsec.java.cache.controller;

import com.intecsec.java.cache.service.LocalCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {
        return localCacheService.get(key);
    }

    @GetMapping("/list/{key}")
    public List<String> list(@PathVariable("key") String key) {
        return localCacheService.getList(key);
    }

    @GetMapping("/delMapCache/{key}")
    public String delMapCache(@PathVariable("key") String key) {
        localCacheService.delMapCache(key);
        return key;
    }

    @GetMapping("/delGuavaCache/{key}")
    public String delGuavaCache(@PathVariable("key") String key) {
        localCacheService.delGuavaCache(key);
        return key;
    }
}
