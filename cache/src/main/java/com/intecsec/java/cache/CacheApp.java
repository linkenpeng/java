package com.intecsec.java.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author peter.peng
 * @date 2020/7/27
 */
@SpringBootApplication
@ImportResource({ "classpath:spring-redis.xml" })
public class CacheApp {

	public static void main(String[] args) {
		SpringApplication.run(CacheApp.class, args);
	}
}
