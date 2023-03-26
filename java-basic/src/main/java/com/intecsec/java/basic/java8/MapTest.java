package com.intecsec.java.basic.java8;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-03-25 22:39
 **/
public class MapTest {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        map.forEach((key, value) -> System.out.println(value));

        // 如下：对 key 为 3 的值，内部会先判断值是否存在，存在，则做 value + key 的拼接操作
        map.computeIfPresent(3, (num, val) -> val + num);
        System.out.println(map.get(3));

        // 先判断 key 为 9 的元素是否存在，存在，则做删除操作
        map.computeIfPresent(9, (num, val) -> null);
        System.out.println(map.containsKey(9));

        // 如下：先判断 key 为 23 的元素是否存在，不存在，则添加
        map.computeIfAbsent(23, num -> "val" + num);
        System.out.println(map.containsKey(23));

        // 先判断 key 为 3 的元素是否存在，存在，则不做任何处理
        map.computeIfAbsent(3, num -> "bam");
        System.out.println(map.get(3));

        // 只有当给定的 key 和 value 完全匹配时，才会执行删除操作
        map.remove(3, "val3");
        System.out.println(map.get(3));

        // 若 key 42 不存在，则返回 not found, 但是不会给key赋值
        System.out.println(map.getOrDefault(42, "not found"));
        // null
        System.out.println(map.get(42));

        // merge 方法，会先判断进行合并的 key 是否存在，不存在，则会添加元素
        map.merge(29, "val29", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(29));          // val9

        // 若 key 的元素存在，则对 value 执行拼接操作
        map.merge(29, "concat", (value, newValue) -> value.concat(newValue));
        System.out.println(map.get(29));          // val9concat
    }
}
