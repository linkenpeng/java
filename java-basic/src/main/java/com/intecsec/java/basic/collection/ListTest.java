package com.intecsec.java.basic.collection;

import com.google.common.base.Joiner;
import com.intecsec.java.basic.keyword.Utils;

import java.text.DecimalFormat;
import java.util.*;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-05-07 09:10
 **/
public class ListTest {
    public static final int NUM = 100000;

    public static void main(String[] args) {
        join();
    }

    public static void join() {
        Joiner joiner = Joiner.on(",");
        for (int i = 0; i < 1; i++) {
            List<String> list = Arrays.asList("a", "b", "c", "d");
            List<Integer> list2 = Arrays.asList(1, 2, 3, 4);
            System.out.println(joiner.join(list));
            System.out.println(joiner.join(list2));
        }
    }

    public static void list() {
        System.out.println("ArrayList================================");
        List<Integer> arrList = new ArrayList();

        for(int i = 0; i < 10; i++) {
            arrList.add(i);
        }
        System.out.println(arrList);
        System.out.println(arrList.get(3));
        arrList.remove(3);
        System.out.println(arrList);
        arrList.add(3, 19);
        System.out.println(arrList);

        long startTime = System.nanoTime();
        for(int i = 0; i < NUM; i++) {
            arrList.add(0, i);
        }
        Utils.duration(startTime);

        traverseByIterator(arrList);
        traverseByIndex(arrList);
        traverseByFor(arrList);
    }

    public static void linkedList() {
        System.out.println("LinkedList================================");
        List<Integer> arrList = new LinkedList();

        for(int i = 0; i < 10; i++) {
            arrList.add(i);
        }
        System.out.println(arrList);
        System.out.println(arrList.get(3));
        arrList.remove(3);
        System.out.println(arrList);
        arrList.add(3, 19);
        System.out.println(arrList);

        long startTime = System.nanoTime();
        for(int i = 0; i < NUM; i++) {
            arrList.add(0, i);
        }
        Utils.duration(startTime);

        traverseByIterator(arrList);
        traverseByIndex(arrList);
        traverseByFor(arrList);
    }

    public static void vector() {
        System.out.println("Vector================================");
        Vector arrList = new Vector();

        for(int i = 0; i < 10; i++) {
            arrList.add(i);
        }
        System.out.println(arrList);
        System.out.println(arrList.get(3));
        arrList.remove(3);
        System.out.println(arrList);
        arrList.add(3, 19);
        System.out.println(arrList);

        long startTime = System.nanoTime();
        for(int i = 0; i < NUM; i++) {
            arrList.add(i);
        }
        Utils.duration(startTime);

        traverseByIterator(arrList);
        traverseByIndex(arrList);
        traverseByFor(arrList);
    }

    public static void traverseByIterator(List list) {
        long startTime = System.nanoTime();
        System.out.println("=======迭代器遍历=======");
        Iterator iterator = list.iterator();
        while(iterator.hasNext()) {
            iterator.next();
        }
        Utils.duration(startTime);
    }



    public static void traverseByIndex(List list) {
        long startTime = System.nanoTime();
        System.out.println("=======随机索引遍历=======");
        for (int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        Utils.duration(startTime);
    }

    public static void traverseByFor(List<Integer> list) {
        long startTime = System.nanoTime();
        System.out.println("=======for遍历=======");
        for(Integer i : list) {
            ;
        }
        Utils.duration(startTime);
    }


}
