package com.intecsec.java.cache.controller;

import com.intecsec.java.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ids")
@Slf4j
public class IdsCacheController {

    @Resource
    private CacheService cacheService;


    @GetMapping("/500")
    public Object ids500(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        return cacheService.getFromRedis("ids_500");
    }

    @GetMapping("/20")
    public Object ids20(@RequestParam(value = "key", required = false,
            defaultValue = "1") String key) {
        return cacheService.getFromRedis("ids_20");
    }

}
