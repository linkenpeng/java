package com.intecsec.java.basic.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 泛型数组
 * @author: peter.peng
 * @create: 2019-09-27 16:44
 **/
public class GenericArray {

    public static void main(String[] args) {
        list1();
    }

    public static void list1() {
        //List<String>[] ls = new ArrayList<String>[10];  not ok
        List<?>[] ls = new ArrayList<?>[10];
        List<String>[] ls1 = new ArrayList[10];

        Object o = ls;
        Object[] oa = (Object[]) o;

        List<Integer> li = new ArrayList<>();
        li.add(new Integer(3));

        oa[1] = li;

        Integer i = (Integer) ls[1].get(0);

        System.out.println(i);

    }
}
