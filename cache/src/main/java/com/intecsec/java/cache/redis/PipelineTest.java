package com.intecsec.java.cache.redis;

import redis.clients.jedis.*;
import redis.clients.jedis.util.JedisClusterCRC16;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @description:
 * @author: peter.peng
 * @create: 2021-03-06 09:23
 **/
public class PipelineTest {


    public static void main(String[] args) throws UnsupportedEncodingException {
        PipelineTest client = new PipelineTest();
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("node1",20249));
        nodes.add(new HostAndPort("node2",20508));
        nodes.add(new HostAndPort("node3",20484));
        String redisPassword = "123456";
        //测试
        client.jedisCluster(nodes,redisPassword);
        client.clusterPipeline(nodes,redisPassword);
    }

    //普通JedisCluster 批量写入测试
    public void jedisCluster(Set<HostAndPort> nodes,String redisPassword) throws UnsupportedEncodingException {
        JedisCluster jc = new JedisCluster(nodes, 2000, 2000,100,redisPassword, new JedisPoolConfig());
        List<String> setKyes = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            setKyes.add("single"+i);
        }
        long start = System.currentTimeMillis();
        for(int j = 0;j < setKyes.size();j++){
            jc.setex(setKyes.get(j),100,"value"+j);
        }
        System.out.println("JedisCluster total time:"+(System.currentTimeMillis() - start));
    }

    //JedisCluster Pipeline 批量写入测试
    public void clusterPipeline(Set<HostAndPort> nodes,String redisPassword) {
        JedisClusterPipeline jedisClusterPipeline = new JedisClusterPipeline(nodes, 2000, 2000,10,redisPassword, new JedisPoolConfig());
        JedisSlotAdvancedConnectionHandler jedisSlotAdvancedConnectionHandler = jedisClusterPipeline.getConnectionHandler();
        Map<JedisPool, List<String>> poolKeys = new HashMap<>();
        List<String> setKyes = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            setKyes.add("pipeline"+i);
        }
        long start = System.currentTimeMillis();
        //查询出 key 所在slot ,通过 slot 获取 JedisPool ,将key 按 JedisPool 分组
        jedisClusterPipeline.refreshCluster();
        for(int j = 0;j < setKyes.size();j++){
            String key = setKyes.get(j);
            int slot = JedisClusterCRC16.getSlot(key);
            JedisPool jedisPool = jedisSlotAdvancedConnectionHandler.getJedisPoolFromSlot(slot);
            if (poolKeys.keySet().contains(jedisPool)){
                List<String> keys = poolKeys.get(jedisPool);
                keys.add(key);
            }else {
                List<String> keys = new ArrayList<>();
                keys.add(key);
                poolKeys.put(jedisPool, keys);
            }
        }
        //调用Jedis pipeline进行单点批量写入
        for (JedisPool jedisPool : poolKeys.keySet()) {
            Jedis jedis = jedisPool.getResource();
            Pipeline pipeline = jedis.pipelined();
            List<String> keys = poolKeys.get(jedisPool);
            for(int i=0;i<keys.size();i++){
                pipeline.setex(keys.get(i),100, "value" + i);
            }
            pipeline.sync();//同步提交
            jedis.close();
        }
        System.out.println("JedisCluster Pipeline total time:"+(System.currentTimeMillis() - start));
    }
}
