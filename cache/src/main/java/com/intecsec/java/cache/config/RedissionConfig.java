package com.intecsec.java.cache.config;

import org.apache.commons.lang3.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @description:
 * @author: peter.peng
 * @create: 2024-07-20 09:46
 **/
@Configuration
@ConditionalOnProperty(name = "spring.redis.redisson.database2", havingValue = "true")
public class RedissionConfig {
    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port:6379}")
    private int port;

    @Value("${spring.redis.password:#{null}}")
    private String password;

    @Resource
    private RedisConfig redisConfig;

    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        String redisUrl = String.format("redis://%s:%d", redisConfig.getHost(), redisConfig.getPort());
        config.useSingleServer().setAddress(redisUrl);
        if(StringUtils.isNotEmpty(redisConfig.getPassword())) {
            config.useSingleServer().setPassword(redisConfig.getPassword());
        }
        config.useSingleServer().setDatabase(2);
        config.useSingleServer().setConnectionMinimumIdleSize(10);
        return Redisson.create(config);
    }

}