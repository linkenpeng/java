package com.intecsec.java.basic.collection;

import com.intecsec.java.basic.keyword.Utils;

import java.io.*;
import java.util.*;

public class MapTest {
	public static void main(String[] args) {
		testTraverseMap();
	}

	public static void treeMap() {
		System.out.println("treeMap============");
		Map<Integer, String> map = new TreeMap<>();
		initMap(map);
		System.out.println(map);
		System.out.println(new TreeMap<>(map).descendingMap());
		traverseByMapEntry(map);
		traverseByKeySet(map);
	}

	public static void testTraverseMap() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("a", "a");
		map.put("c", "c");
		map.put("f", "f");
		map.put("b", "b");

		Map<String, String> newMap = new HashMap<>();
		map.forEach((k, v) -> {
			if("c".equals(v)) {
				return;
			}
			newMap.put(k, v);
		});

		System.out.println("newMap" + newMap);

		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			String key = entry.getKey();
			sb.append(key + "=" + map.get(key) + "&");
		}
		System.out.println(sb.toString().substring(0, sb.length() - 1));

		StringBuilder sb1 = new StringBuilder();
		for (String key : map.keySet()) {
			sb1.append(key + "=" + map.get(key) + "&");
		}
		System.out.println(sb1.substring(0, sb1.length() - 1));
		System.out.println(map.getOrDefault("e", "ee"));

		// traverseMap(map);
	}

	public static void traverseMap(Map<String, String> map) {
		// 1.
		for (String key : map.keySet()) {
			System.out.println(key + ":" + map.get(key));
		}

		// 2.
		Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, String> entry = iterator.next();
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

		// 3. 推荐的方法
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}

		// 4.
		for (String val : map.values()) {
			System.out.println(val);
		}
	}

	public static void initMap(Map<Integer, String> map) {
		map.put(1, "a");
		map.put(2, null);
		map.put(3, "b");

		System.out.println(map.containsKey(3));
		System.out.println(map.containsValue("a"));
		map.remove(2);
		System.out.println(map.size());

		Random random = new Random();
		for(int i = 0; i < 10000; i++) {
			map.put(i,"" + random.nextInt());
		}
	}

	public static void hashMap() {
		Map<Integer, String> map = new HashMap<>();
		initMap(map);
		traverseByMapEntry(map);
		traverseByKeySet(map);
	}

	public static void traverseByMapEntry(Map<Integer, String> map) {
		System.out.println("traverseByMapEntry =====");
		long startTime = System.nanoTime();
		for(Map.Entry<Integer, String> entry : map.entrySet()) {
			entry.getValue();
		}
		Utils.duration(startTime);
	}

	public static void traverseByKeySet(Map<Integer, String> map) {
		System.out.println("traverseByKeySet =====");
		long startTime = System.nanoTime();
		Set<Integer> keys = map.keySet();
		for(Integer key : keys) {
			map.get(key);
		}
		Utils.duration(startTime);
	}

	public static void traverseByEnumeration(Hashtable<Integer, String> hashtable) {
		System.out.println("traverseByEnumeration =====");
		long startTime = System.nanoTime();
		Enumeration<Integer> keys = hashtable.keys();
		Integer key;
		while (keys.hasMoreElements()) {
			key = keys.nextElement();
			hashtable.get(key);
		}

		Utils.duration(startTime);
	}

	public static void linkedHashMap() {
		System.out.println("linkedHashMap============");
		Map<Integer, String> map = new LinkedHashMap<>();
		initMap(map);
		traverseByMapEntry(map);
		traverseByKeySet(map);
	}
}
