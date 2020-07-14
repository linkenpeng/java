package com.intecsec.java.basic.datastructure.sort;

import com.intecsec.java.basic.datastructure.array.BaseArray;

/**
 * @description: 插入排序
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
