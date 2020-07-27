package com.intecsec.java.datastructure.array;

/**
 * @description: LowArray测试
 * @author: peter.peng
 * @create: 2019-01-17 15:10
 **/
public class HignArrayApp {

    public static void main(String[] args) {
        HighArray arr;
        arr = new HighArray(100);

        arr.insert(77);
        arr.insert(99);
        arr.insert(44);
        arr.insert(55);
        arr.insert(22);
        arr.insert(88);
        arr.insert(11);
        arr.insert( 00);
        arr.insert(66);
        arr.insert(3);
        arr.display();

        // search
        int searchKey = 26;
        if(arr.find(searchKey)) {
            System.out.println("Fond :" + searchKey);
        } else {
            System.out.println("Can't find:" + searchKey);
        }

        // del
        arr.delete(55);
        arr.delete(00);
        arr.delete(66);

        arr.display();
    }
}
