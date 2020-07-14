package com.intecsec.java.basic.datastructure.array;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-01-17 15:10
 **/
public class ArrayInsObjApp {

    public static void main(String[] args) {
        ArrayInsObj arr;
        arr = new ArrayInsObj(100);

        arr.inseart("Evans", "Patty", 24);
        arr.inseart("Smith", "Doc", 59);
        arr.inseart("Smith", "Lorraine", 37);
        arr.inseart("Smith", "Paul", 37);
        arr.inseart("Yee", "Tom", 43);
        arr.inseart("Hashimoto", "Sato", 21);
        arr.inseart("Stimson", "Henry", 29);
        arr.inseart("Velasquez", "Jose", 72);
        arr.inseart("Vang", "Minh", 22);
        arr.inseart("Creswell", "Lucinda", 18);

        arr.displayA();

        arr.insertionSort();

        System.out.println("After sorting:");

        arr.displayA();
    }
}
