package com.intecsec.java.datastructure.sort;


import com.intecsec.java.datastructure.array.BaseArray;

/**
 * @description: 选择排序 O(n²)
 *  特点： 交换次数少，但是比较次数还是很大，
 * 数据量小，并且交换数据相对于比较数据更加耗时的情况下，可以选择选择排序
 *
 * @author: peter.peng
 * @create: 2019-01-17 15:07
 **/
public class ArraySel extends BaseArray {

    public ArraySel(int max) {
        super(max);
    }

    // 选择排序
    public void selectionSort() {
        int out, in, min;
        for(out = 0; out < nElems - 1; out++) {
            min = out;
            for(in = out + 1; in < nElems; in++) {
                if(a[in] < a[min]) {
                    min = in;
                }
            }
            swap(out, min);
        }
    }
}
