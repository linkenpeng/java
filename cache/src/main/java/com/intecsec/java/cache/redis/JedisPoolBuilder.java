package com.intecsec.java.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Peter.Peng
 * @date 2021/2/19
 */
public class JedisPoolBuilder {

	public static final int MAX_IDLE = 50;
	public static final int MAX_TOTAL = 50;
	public static final int PORT = 6379;
	public static final int TIMEOUT = 10000;
	public static final String ADDR = "dev.redis.intecsec.com";
	private static JedisPool pool = null;

	static {
		buildPool();
		hotPool();
	}

	private static JedisPool buildPool() {
		if(pool == null) {
			long start = System.currentTimeMillis();
			JedisPoolConfig config = new JedisPoolConfig();
			config.setMaxTotal(MAX_TOTAL);
			config.setMaxIdle(MAX_IDLE);
			config.setMaxWaitMillis(1000 * 10);
			config.setTestOnBorrow(true);
			pool = new JedisPool(config, ADDR, PORT, TIMEOUT);
			long end = System.currentTimeMillis();
			System.out.println("buildPool毫秒数: " + (end - start));
		}
		return pool;
	}

	public static void hotPool() {
		long start = System.currentTimeMillis();

		List<Jedis> minIdelJedisList = new ArrayList<>(MAX_IDLE);
		Jedis jedis = null;
		for(int i = 0; i < MAX_IDLE; i++) {
			jedis = pool.getResource();
			minIdelJedisList.add(jedis);
			jedis.ping();
		}
		for(int i = 0; i < MAX_IDLE; i++) {
			jedis = minIdelJedisList.get(i);
			jedis.close();
		}

		long end = System.currentTimeMillis();
		System.out.println("hotPool毫秒数: " + (end - start));
	}

	public static Jedis getJedis() {
		return pool.getResource();
	}
}
