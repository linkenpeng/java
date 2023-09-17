package com.intecsec.java.cache;

import com.intecsec.java.cache.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author peter.peng
 * @date 2020/7/27
 */
@SpringBootApplication
@EnableCaching
@Slf4j
public class CacheApp {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(CacheApp.class, args);
	}
}
