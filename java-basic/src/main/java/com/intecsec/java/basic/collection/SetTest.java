package com.intecsec.java.basic.collection;

import java.util.*;

/**
 * @description:
 * @author: peter.peng
 * @create: 2020-04-23 15:31
 **/
public class SetTest {

    public static void main(String[] args) {

        hashset();
        linkedHashSet();
        treeSet();


    }


    public static void  hashset() {
        System.out.println("HashSet =================================");

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

        initData(set);
        traverseForIterator(set);
        traverseForFor(set);
    }

    public static void traverseForIterator(Set<Integer> set) {
        System.out.println("traverseForIterator============");
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    public static void traverseForFor(Set<Integer> set) {
        System.out.println("traverseForFor============");
        for(Integer i : set) {
            System.out.println(i);
        }
    }

    public static void initData(Set<Integer> set) {
        // HashSet LinkedHashSet 会调用 hashcode equals
        // TreeSet 会调用 compareTo
        set.add(1);
        set.add(3);
        set.add(10);
        set.add(6);
        set.add(40);
        set.add(30);
    }

    public static void  linkedHashSet() {
        System.out.println("LinkedHashSet =================================");
        Set<Integer> set = new LinkedHashSet();
        initData(set);
        traverseForIterator(set);
        traverseForFor(set);
    }

    public static void  treeSet() {
        System.out.println("TreeSet =================================");
        Set<Integer> set = new TreeSet();
        initData(set);
        traverseForIterator(set);
        traverseForFor(set);

    }
}
