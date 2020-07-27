package com.intecsec.java.basic.collection;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MapTest {
	public static void main(String[] args) {
		testTraverseMap();
	}

	public static void treeMap() {
		Map<String, String> map = new TreeMap<>();
		map.put("1", "1");
		map.put("5", "5");
		map.put("2", "2");
		map.put("6", "6");
		map.put("3", "3");
		map.put("4", "4");
		System.out.println(map);
		System.out.println(new TreeMap<>(map).descendingMap());

		/*Map<String, String> treeMap = new TreeMap<>();
		treeMap.putAll(map);
		System.out.println(treeMap);*/
	}

	public static void testTraverseMap() {
		Map<String, String> map = new TreeMap<String, String>();
		map.put("a", "a");
		map.put("c", "c");
		map.put("f", "f");
		map.put("b", "b");

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
}
