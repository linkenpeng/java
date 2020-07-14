package com.intecsec.java.basic.datastructure.sort;

import com.intecsec.java.basic.datastructure.array.BaseArray;

/**
 * @description: 冒泡排序
 * @author: peter.peng
 * @create: 2019-01-17 15:07
 **/
public class ArrayBub extends BaseArray {

    public ArrayBub(int max) {
        super(max);
    }

    public void bubbleSort() {
        int out, in;
        for(out = nElems - 1; out > 1; out--) {
            for(in = 0; in < out; in++) {
                if(a[in] > a[in+1]) {
                    swap(in, in+1);
                }
            }
        }
    }

}
