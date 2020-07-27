package com.intecsec.java.datastructure.sort;

/**
 * @description: 选择排序测试
 * @author: peter.peng
 * @create: 2019-01-17 15:10
 **/
public class ArraySortApp {

    public static void main(String[] args) {
        selSort();
        insSort();
        bubSort();
        shellSort();
        quickSort();
    }

    public static void selSort() {
        ArraySel arr;
        arr = new ArraySel(100);
        arr.initArray();

        arr.display();

        arr.selectionSort();

        arr.display();
    }

    public static void insSort() {
        ArrayIns arr;
        arr = new ArrayIns(100);
        arr.initArray();

        arr.display();

        arr.insertionSort();

        arr.display();
    }

    public static void bubSort() {
        ArrayBub arr;
        arr = new ArrayBub(100);
        arr.initArray();

        arr.display();

        arr.bubbleSort();

        arr.display();
    }

    public static void shellSort() {
        ShellSort arr;
        arr = new ShellSort(100);
        arr.initArray();

        arr.display();

        arr.sort();

        arr.display();
    }

    public static void quickSort() {
        QuickSort arr;
        arr = new QuickSort(100);
        arr.initArray();

        arr.display();

        arr.sort();

        arr.display();
    }
}
