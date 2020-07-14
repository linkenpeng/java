package com.intecsec.java.basic.collection;

import java.util.HashSet;
import java.util.Set;

/**
 * @description:
 * @author: peter.peng
 * @create: 2020-04-23 15:31
 **/
public class SetTest {

    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(1);
        set.add(2);
        System.out.println(set.contains(1));
        set.add(1);
        System.out.println(set);

        Set<String> sets = new HashSet<>();
        sets.add("1");
        sets.add("2");
        System.out.println(sets.contains("1"));
        sets.add("1");
        System.out.println(sets);
    }
}
