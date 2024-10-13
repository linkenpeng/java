package com.intecsec.java.cache.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Tuple;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;
import java.util.zip.GZIPInputStream;

/**
 * @author Peter.Peng
 * @date 2021/2/10
 */
public class JedisTest {

	public static void main(String[] args) {
		// Jedis jedis = getJedis();

		// Jedis实现了Closeable 因此可以try-with-resources
		try(Jedis jedis = JedisPoolBuilder.getJedis()) {
			testUnGzipString(jedis);
			// testString(jedis);

			// testList(jedis);

			// testSet(jedis);

			// testZset(jedis);

			// testHash(jedis);

			// testPipLine(jedis);
		}

		// jedis.close();
	}

	/**
	 * 批量删除： redis-cli keys "jedis:*" |xargs redis-cli del
	 *
	 * @param jedis
	 */
	public static void testPipLine(Jedis jedis) {
		Pipeline pipeline = jedis.pipelined();
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++) {
			pipeline.set("pipline:test_" + i, i + "");
		}
		pipeline.sync();
		long endTime = System.currentTimeMillis();
		System.out.println("pipline time is: " + (endTime - startTime));

		startTime = System.currentTimeMillis();
		for(int i = 0; i < 10000; i++) {
			jedis.set("jedis:test_" + i, i + "");
		}
		endTime = System.currentTimeMillis();
		System.out.println("jedis single set time is: " + (endTime - startTime));
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

		Map<String, Double> members = new HashMap<>();
		members.put("u01", 1000.0);
		members.put("u02", 2000.0);
		members.put("u03", 3000.0);
		members.put("u04", 13000.0);
		members.put("u05", 23000.0);

		jedis.zadd(key, members);
		System.out.println(jedis.type(key)); // zset

		System.out.println(jedis.zcard(key)); // 5
		System.out.println(jedis.zrange(key, 0, -1)); // [u01, u02, u03, u04, u05]
		System.out.println(jedis.zrevrange(key, 0, -1)); // [u05, u04, u03, u02, u01]
		System.out.println(jedis.zrangeByScore(key, 1000, 10000)); // [u01, u02, u03]

		Set<Tuple> res0 = jedis.zrangeByScoreWithScores(key, 1000, 10000);
		for(Tuple t : res0) {
			System.out.println(t.getElement() + "->" + t.getScore()); // u01->1000.0
		}

		System.out.println(jedis.zcount(key, 1000, 10000)); // 3

		System.out.println(jedis.zscore(key, "u01")); // 1000.0
		System.out.println(jedis.zrank(key, "u01")); // 0
		System.out.println(jedis.zrevrank(key, "u01")); // 4

		System.out.println("zrem ==");
		jedis.zrem(key, "u01", "u02");
		System.out.println(jedis.zremrangeByRank(key, 0, 1)); // 2
		System.out.println(jedis.zremrangeByScore(key, 20000, 30000)); // 1
		System.out.println(jedis.zrange(key, 0, -1)); // []

		Map<String, Double> members2 = new HashMap<>();
		members2.put("ul1", 1000.0);
		members2.put("ul2", 2000.0);
		members2.put("ul3", 3000.0);

		jedis.zadd(key, members2);
		jedis.zincrby(key2, 10000, "ul3");
		System.out.println(jedis.zrange(key2, 0, -1)); // [ul3]

		jedis.del(key);
		jedis.del(key2);
	}

	public static void testSet(Jedis jedis) {
		String key = "set1";
		jedis.del(key);
		jedis.sadd(key, "user01", "user02", "user03");
		System.out.println(jedis.smembers(key)); // [user02, user01, user03]
		System.out.println(jedis.scard(key)); // 3
		System.out.println(jedis.srem(key, "user03")); // 1 删除user03
		System.out.println(jedis.smembers(key)); // [user02, user01]
		jedis.del(key);
	}

	public static void testHash(Jedis jedis) {
		String key = "config";
		jedis.del(key);
		jedis.hset(key, "ip", "127.0.0.1");

		System.out.println(jedis.type(key)); // hash
		// System.out.println(jedis.get(key)); // error
		System.out.println(jedis.hget(key, "ip")); // 127.0.0.1

		Map<String, String> configFields = new HashMap<>();
		configFields.put("port", "8080");
		configFields.put("maxAlive", "3600");
		configFields.put("weight", "1.0");
		jedis.hmset(key, configFields);

		System.out.println(jedis.hgetAll(key)); // {weight=1.0, port=8080, maxAlive=3600, ip=127.0.0.1}
		System.out.println(jedis.hmget(key, "ip", "port")); // [127.0.0.1, 8080]

		jedis.hincrByFloat("key", "weight", 1.2);
		System.out.println(jedis.hget(key, "weight")); // 1.0 无法自增

		System.out.println(jedis.hkeys(key)); // [weight, port, maxAlive, ip]
		System.out.println(jedis.hvals(key)); // [127.0.0.1, 1.0, 8080, 3600]

		System.out.println(jedis.hlen(key)); // 3
		System.out.println(jedis.hexists(key, "weight")); // true
		System.out.println(jedis.hdel(key, "weight")); // 1
		System.out.println(jedis.hexists(key, "weight")); // false

		jedis.del(key);
	}

	public static void testList(Jedis jedis) {
		jedis.del("list1");
		jedis.lpush("list1", "zhangsan", "lisi", "wangwu");
		jedis.lpush("list1", "zhaoliu");
		jedis.lpush("list1", "qianqi");
		jedis.rpush("list1", "first");

		System.out.println(jedis.type("list1")); // list

		// 队列
		System.out.println(jedis.lrange("list1", 0, -1)); // [qianqi, zhaoliu, wangwu, lisi, zhangsan, first]
		System.out.println(jedis.lrange("list1", 1, 2));  // [zhaoliu, wangwu]
		System.out.println(jedis.llen("list1")); // 6
		System.out.println(jedis.lindex("list1", 1)); // zhaoliu

		// 栈
		System.out.println(jedis.lpop("list1")); // qianqi
		System.out.println(jedis.rpop("list1")); // first
		System.out.println(jedis.lrange("list1", 0, -1)); // [zhaoliu, wangwu, lisi, zhangsan]

		jedis.lset("list1", 0, "lisi2");
		System.out.println(jedis.lrange("list1", 0, -1)); // [lisi2, wangwu, lisi, zhangsan]

		jedis.del("list1");
	}

	public static byte[] compress(String data) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(byteArrayOutputStream)) {
			gzipOutputStream.write(data.getBytes());
		}
		return byteArrayOutputStream.toByteArray();
	}

	public static byte[] decompress(byte[] compressedData) throws IOException {
		ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(compressedData);
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try (GZIPInputStream gzipInputStream = new GZIPInputStream(byteArrayInputStream)) {
			byte[] buffer = new byte[1024];
			int len;
			while ((len = gzipInputStream.read(buffer)) > 0) {
				byteArrayOutputStream.write(buffer, 0, len);
			}
		}
		return byteArrayOutputStream.toByteArray();
	}

	public static String decompressAsString(byte[] compressedData) throws IOException {
		byte[] decompressedData = decompress(compressedData);
        return new String(decompressedData);
	}

	private static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
					+ Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}

	public static String readFile(String filePath) {
		StringBuilder fileContent = new StringBuilder();
		try(BufferedReader reader = new BufferedReader(new InputStreamReader(Files.newInputStream(Paths.get(filePath))))) {
			String line;
			while ((line = reader.readLine()) != null) {
				fileContent.append("2_101058726_");
				fileContent.append(line);
				fileContent.append(',');
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileContent.toString();
	}

	public static void testUnGzipString(Jedis jedis) {
		try {
			String filePath = "test.txt";
			String fileContent = readFile(filePath);
			// System.out.println(fileContent);

			// 压缩写入Redis
			System.out.println("开始压缩...");
			long t1 = System.nanoTime();
			byte[] compressData = compress(fileContent);
			long t2 = System.nanoTime();
			System.out.println("压缩耗时: " + (t2 - t1)/1000 + "微秒");
			String key = "unzip_500";
			String gzipKey = "gzip_500";
			byte[] gzKey = gzipKey.getBytes();
			jedis.set(key, fileContent);
			jedis.set(gzKey, compressData);

			// 从Redis获取数据
			byte[] compressedData = jedis.get(gzKey);
			// 获取键的内存使用量
			long gzMemoryUsage = jedis.memoryUsage(gzKey);
			System.out.println("Memory usage of gzKey '" + gzKey + "': " + gzMemoryUsage + " bytes");
			// 获取键的内存使用量
			long memoryUsage = jedis.memoryUsage(key);
			System.out.println("Memory usage of key '" + key + "': " + memoryUsage + " bytes");

			String decompressedStringData;

			System.out.println("开始解压...");
			t1 = System.nanoTime();
			decompressedStringData = decompressAsString(compressedData);
			t2 = System.nanoTime();
			System.out.println("解压耗时: " + (t2 - t1)/1000 + "微秒");

			System.out.println("decompressedStringData: " + decompressedStringData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void testString(Jedis jedis) {
		jedis.set("key0", "123456");
		System.out.println(jedis.get("key0"));
		System.out.println(jedis.exists("key0"));
		System.out.println(jedis.strlen("key0"));

		System.out.println("test getrange ====");
		System.out.println(jedis.getrange("key0", 0, -1));
		System.out.println(jedis.getrange("key0", 0, 4));

		System.out.println("test append ====");
		System.out.println(jedis.append("key0", "appendStr"));
		System.out.println(jedis.get("key0"));

		System.out.println("test rename key ====");
		jedis.rename("key0", "key0_new");
		System.out.println(jedis.exists("key0"));

		System.out.println("test mset mget ====");
		jedis.mset("key1","va11","key2","va12","key3","100");
		System.out.println(jedis.mget("key1", "key2", "key3"));
		jedis.del("key1");
		System.out.println(jedis.exists("key1")); // false

		System.out.println("test getSet ====");
		System.out.println(jedis.getSet("key2", "val22")); // va12 redis val22
		System.out.println(jedis.get("key2")); // val22
		System.out.println(jedis.getSet("key4", "val4")); // null
		System.out.println(jedis.get("key4")); // val4

		System.out.println("test incr incrBy ====");
		jedis.incr("key3");
		System.out.println(jedis.get("key3")); // 101
		jedis.incrBy("key3", 15); // 116
		System.out.println(jedis.get("key3"));

		System.out.println("test decr decrBy ====");
		jedis.decr("key3");
		System.out.println(jedis.get("key3")); // 115
		jedis.decrBy("key3", 20);
		System.out.println(jedis.get("key3")); // 95

		System.out.println("test incrByFloat ====");
		jedis.incrByFloat("key3", 1.1);
		System.out.println(jedis.get("key3")); // 96.1

		System.out.println("test setnx msetnx ====");
		jedis.setnx("key3", "exitsVal");
		System.out.println(jedis.get("key3")); // 96.1
		jedis.msetnx("key2", "exists1", "key2", "exists2");
		jedis.setex("key4", 2, "2 seconds is no val");

		System.out.println("test setrange ====");
		jedis.set("key6", "123456789");
		System.out.println(jedis.get("key6"));
		jedis.setrange("key6", 3, "abcdefg");
		System.out.println(jedis.get("key6"));

		System.out.println("test keys ====");
		System.out.println(jedis.keys("key*"));
		for(String s : jedis.keys("key*")) {
			jedis.del(s);
		}
		System.out.println(jedis.keys("key*"));
	}

}
