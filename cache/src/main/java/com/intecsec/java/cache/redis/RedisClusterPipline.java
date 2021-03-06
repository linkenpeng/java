package com.intecsec.java.cache.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisClusterConnection;
import org.springframework.data.redis.core.RedisConnectionUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Component;
import redis.clients.jedis.*;
import redis.clients.jedis.util.JedisClusterCRC16;

import java.lang.reflect.Field;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-03-06 11:06
 **/
@Component
public class RedisClusterPipline {

    @Autowired
    private RedisTemplate redisTemplate;


    public void op(String key) throws Exception {
        RedisConnectionFactory factory = redisTemplate.getConnectionFactory();
        RedisConnection redisConnection = factory.getConnection();
        JedisClusterConnection jedisClusterConnection = (JedisClusterConnection) redisConnection;
        JedisCluster jedisCluster = jedisClusterConnection.getNativeConnection();
        int slot = JedisClusterCRC16.getSlot(key);
        /**
         *  不建议这么使用，官方在2.10版本已经修复<a href="https://github.com/xetorthio/jedis/pull/1532">此问题</a><br>
         *  2.10版本中，官方会直接提供JedisCluster#getConnectionFromSlot
         */
        Field field = ReflectionUtils.findField(BinaryJedisCluster.class, null, true);
        field.setAccessible(true);
        JedisSlotBasedConnectionHandler jedisClusterConnectionHandler = (JedisSlotBasedConnectionHandler) field.get(jedisCluster);

        // 接下来就是pipeline操作了
        Jedis jedis = jedisClusterConnectionHandler.getConnectionFromSlot(slot);
        Pipeline pipeline = jedis.pipelined();
        for(int i = 0; i < 10000; i++) {
            jedis.set("jedis:test_" + i, i + "");
        }
        pipeline.syncAndReturnAll();
        jedis.close();


        RedisConnectionUtils.releaseConnection(redisConnection, factory);
    }

}
