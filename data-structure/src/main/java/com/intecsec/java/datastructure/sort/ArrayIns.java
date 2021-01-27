package com.intecsec.java.datastructure.sort;


import com.intecsec.java.datastructure.array.BaseArray;

/**
 * 插入排序 O(n²)
 * @description:
 *
 * 在大多数情况下，假设数据量比较小或者基本上有序时，插入排序是三种简单排序算法中最好的选择
 *
 * @author: peter.peng
 * @create: 2019-01-17 15:07
 **/
public class ArrayIns extends BaseArray {

    public ArrayIns(int max) {
        super(max);
    }

    public void insertionSort() {
        int out, in;
        for(out = 1; out < nElems; out++) {
            long temp = a[out];
            in = out;
            while (in > 0 && a[in-1] >= temp) {
                a[in] = a[in-1];
                --in;
            }
            a[in] = temp;
        }
    }
}
