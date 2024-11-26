package com.intecsec.java.cache.redis;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class JedisClusterFactory {

    public static Properties getProperties() {
        String filename = "redis.properties";
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filename)
        ) {
            properties.load(input);
            return properties;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static JedisCluster getJedisCluster() {
        Properties prop = getProperties();
        String prefix = "redis.cluster.";
        String host = prop.getProperty(prefix + "host");
        String connectionTimeout = prop.getProperty(prefix + "connectionTimeout");
        String soTimeout = prop.getProperty(prefix + "soTimeout");
        String maxAttempts = prop.getProperty(prefix + "maxAttempts");
        System.out.println(prefix);
        System.out.println(host);

        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("node1",20249));
        nodes.add(new HostAndPort("node2",20508));
        nodes.add(new HostAndPort("node3",20484));
        String redisPassword = "123456";
        return new JedisCluster(nodes, 2000,
                2000,100,redisPassword, new JedisPoolConfig());
    }

    public static void main(String[] args) {
        getJedisCluster();
    }
}
