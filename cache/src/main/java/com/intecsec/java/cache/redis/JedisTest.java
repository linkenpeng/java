package com.intecsec.java.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author Peter.Peng
 * @date 2021/2/10
 */
public class JedisTest {

	public static void main(String[] args) {
		// Jedis jedis = getJedis();

		// Jedis实现了Closeable 因此可以try-with-resources
		try(Jedis jedis = JedisPoolBuilder.getJedis()) {
			testZset(jedis);
		}

		// jedis.close();
	}

	public static Jedis getJedis() {
		Jedis jedis = new Jedis("dev.redis.intecsec.com", 6379);
		System.out.println("jedis.ping(): " + jedis.ping());
		return jedis;
	}

	public static void testZset(Jedis jedis) {
		String key = "salary";
		String key2 = "salary2";
		jedis.del(key);
		System.out.println(jedis.type(key));

		Map<String, Double> members = new HashMap<>();
		members.put("u01", 1000.0);
		members.put("u02", 2000.0);
		members.put("u03", 3000.0);
		members.put("u04", 13000.0);
		members.put("u05", 23000.0);

		jedis.zadd(key, members);
		System.out.println(jedis.zcard(key));
		System.out.println(jedis.zrange(key, 0, -1));
		System.out.println(jedis.zrevrange(key, 0, -1));

		System.out.println(jedis.zrangeByScore(key, 1000, 10000));

		Set<Tuple> res0 = jedis.zrangeByScoreWithScores(key, 1000, 10000);
		for(Tuple t : res0) {
			System.out.println(t.getElement() + "->" + t.getScore());
		}

		System.out.println(jedis.zcount(key, 1000, 10000));
		System.out.println(jedis.zscore(key, "u01"));
		System.out.println(jedis.zrank(key, "u01"));
		System.out.println(jedis.zrevrank(key, "u01"));

		System.out.println(jedis.zrem(key, "u01", "u02"));
		System.out.println(jedis.zremrangeByRank(key, 0, 1));
		System.out.println(jedis.zremrangeByScore(key, 20000, 30000));

		System.out.println(jedis.zrange(key, 0, -1));

		Map<String, Double> members2 = new HashMap<>();
		members2.put("ul1", 1000.0);
		members2.put("ul2", 2000.0);
		members2.put("ul3", 3000.0);

		jedis.zadd(key, members2);

		jedis.zincrby(key2, 10000, "ul3");

		System.out.println(jedis.zrange(key2, 0, -1));

		jedis.del(key);
		jedis.del(key2);
	}

	public static void testSet(Jedis jedis) {
		String key = "set1";
		jedis.del(key);
		System.out.println(jedis.type(key));

		jedis.sadd(key, "user01", "user02", "user03");
		System.out.println(jedis.smembers(key));
		System.out.println(jedis.scard(key));
		System.out.println(jedis.srem(key, "user03"));
		System.out.println(jedis.smembers(key));

		jedis.del(key);
	}

	public static void testHash(Jedis jedis) {
		String key = "config";
		jedis.del(key);
		jedis.hset(key, "ip", "127.0.0.1");
		System.out.println(jedis.hget(key, "ip"));
		System.out.println(jedis.type(key));

		Map<String, String> configFields = new HashMap<>();
		configFields.put("port", "8080");
		configFields.put("maxAlive", "3600");
		configFields.put("weight", "1.0");
		jedis.hmset(key, configFields);

		System.out.println(jedis.hgetAll(key));
		System.out.println(jedis.hmget(key, "ip", "port"));

		jedis.hincrByFloat("key", "weight", 1.2);
		System.out.println(jedis.hget(key, "weight"));

		System.out.println(jedis.hkeys(key));
		System.out.println(jedis.hvals(key));

		System.out.println(jedis.hlen(key));
		System.out.println(jedis.hexists(key, "weight"));
		System.out.println(jedis.hdel(key, "weight"));
		System.out.println(jedis.hexists(key, "weight"));

		jedis.del(key);
	}

	public static void testList(Jedis jedis) {
		jedis.del("list1");
		jedis.lpush("list1", "zhangsan", "lisi", "wangwu");
		System.out.println(jedis.type("list1"));
		System.out.println(jedis.lrange("list1", 0, -1));
		System.out.println(jedis.lrange("list1", 1, 2));
		System.out.println(jedis.llen("list1"));
		System.out.println(jedis.lindex("list1", 1));
		System.out.println(jedis.lpop("list1"));
		System.out.println(jedis.rpop("list1"));
		System.out.println(jedis.lset("list1", 0, "lisi2"));
		System.out.println(jedis.lrange("list1", 0, -1));

		jedis.del("list1");
	}

	public static void testString(Jedis jedis) {
		jedis.set("key0", "123456");
		System.out.println(jedis.get("key0"));
		System.out.println(jedis.exists("key0"));
		System.out.println(jedis.strlen("key0"));
		System.out.println(jedis.getrange("key0", 0, -1));
		System.out.println(jedis.getrange("key0", 0, 4));
		System.out.println(jedis.append("key0", "appendStr"));
		System.out.println(jedis.get("key0"));

		jedis.rename("key0", "key0_new");
		System.out.println(jedis.exists("key0"));

		jedis.mset("key1","va11","key2","va12","key3","100");
		System.out.println(jedis.mget("key1", "key2", "key3"));

		jedis.del("key1");
		System.out.println(jedis.exists("key1"));

		jedis.getSet("key2", "val3");

		jedis.incr("key3");
		System.out.println(jedis.get("key3"));
		jedis.incrBy("key3", 15);
		System.out.println(jedis.get("key3"));

		jedis.decr("key3");
		System.out.println(jedis.get("key3"));
		jedis.decrBy("key3", 20);
		System.out.println(jedis.get("key3"));

		jedis.incrByFloat("key3", 1.1);
		jedis.setnx("key3", "exitsVal");
		System.out.println(jedis.get("key3"));

		jedis.msetnx("key2", "exists1", "key2", "exists2");
		jedis.setex("key4", 2, "2 seconds is no val");

		jedis.set("key6", "123456789");
		System.out.println(jedis.get("key6"));
		jedis.setrange("key6", 3, "abcdefg");
		System.out.println(jedis.get("key6"));

		System.out.println(jedis.keys("key*"));

		for(String s : jedis.keys("key*")) {
			jedis.del(s);
		}

		System.out.println(jedis.keys("key*"));
	}

}
