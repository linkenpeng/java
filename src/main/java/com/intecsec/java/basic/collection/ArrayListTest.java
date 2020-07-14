package com.intecsec.java.basic.collection;

import com.intecsec.java.beans.Car;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;

/**
 * @description: ArrayList测试
 * @author: peter.peng
 * @create: 2018-11-29 18:24
 **/
public class ArrayListTest {
    public static void main(String[] args) {
        insert();
    }

    public static void basic() {
        List<Integer> linkedList = new LinkedList();
        List<Integer> arrayList = new ArrayList<>();

        for(int i = 0; i < 20; i++) {
            linkedList.add(i);
            arrayList.add(i);
        }

        System.out.println(linkedList);
        System.out.println(arrayList);

        Integer[] a = arrayList.toArray(new Integer[arrayList.size()]);
        for(int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

        System.out.println(arrayList.toArray());
    }

    private static void insert() {
        Car car1 = new Car("a", "a", 1);
        Car car2 = new Car("b", "b", 2);
        Car car3 = new Car("c", "c", 3);
        Car car4 = new Car("d", "d", 4);

        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);

        System.out.println(cars);

        cars.remove(car3);

        System.out.println(cars);

        cars.add(0, car3);

        System.out.println(cars);
    }

    public static void queue() {
        Queue<String> queue = new LinkedList<>();
        queue.offer("str");
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        // System.out.println(queue.remove());
        System.out.println(queue.size());
    }

    public static void asList() {
        String[] astr = new String[]{"a", "b"};
        System.out.println(Arrays.asList(astr));
        System.out.println(Arrays.asList("a", 1));
    }

    public static void setTest() {
        int paymentId = 50;
        Set<Integer> sets = new HashSet<>();
        sets.add(27);
        sets.add(28);
        sets.add(29);
        sets.add(30);
        sets.add(50);
        String exePaymentId = CollectionUtils.isNotEmpty(sets) && sets.contains(Integer.valueOf(paymentId))
                ? "27" : String.valueOf(paymentId);
        System.out.println(exePaymentId);
    }

    public static void size() {
        List<Integer> integers = new ArrayList<>();
        int current = 0;
        for(int i = 0; i < 52; i++) {
            integers.add(i);
            current++;
            if(current >= 10) {
                System.out.println(integers);
                current = 0;
                integers = new ArrayList<>();
            }
        }
        System.out.println(integers);
    }
}
