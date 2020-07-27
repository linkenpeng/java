package com.intecsec.java.datastructure.sort;


import com.intecsec.java.datastructure.array.BaseArray;

/**
 * @description: 希尔排序 O(N 3/2 方)
 * @author: peter.peng
 * @create: 2019-03-05 15:07
 **/
public class ShellSort extends BaseArray {

    public ShellSort(int max) {
        super(max);
    }

    public void sort() {
        int inner, outer;
        long temp;
        int h = 1;

        while (h <= nElems / 3) {
            h = h * 3 + 1;
        }

        while (h > 0) {
            for(outer = h; outer < nElems; outer++) {
                temp = a[outer];
                inner = outer;

                while (inner > h - 1 && a[inner - h] >= temp) {
                    a[inner] = a[inner - h];
                    inner -= h;
                }
                a[inner] = temp;
            }
            h = (h - 1) / 3;
        }
    }
}
