package com.intecsec.java.datastructure.sort;


import com.intecsec.java.datastructure.array.BaseArray;

/**
 * 快速排序 O(n)
 * @description:
 *
 * 对于大数据量的排序来说，快速排序通常是最快的方法
 *
 * @author: peter.peng
 * @create: 2019-03-05 15:07
 **/
public class QuickSort extends BaseArray {

    public QuickSort(int max) {
        super(max);
    }

    public void sort() {
        recQuickSort(0, nElems - 1);
    }

    public void recQuickSort(int left, int right) {
        if(right - left < 0) {
            return;
        } else {
            long pivot = a[right];
            int partition = partitionIt(left, right, pivot);
            recQuickSort(left, partition - 1);
            recQuickSort(partition + 1, right);
        }
    }

    public int partitionIt(int left, int right, long pivot) {
        int leftPtr = left - 1;
        int rightPtr = right;
        while (true) {
            while (a[++leftPtr] < pivot) {
                ;
            }

            while (rightPtr > 0 && a[--rightPtr] > pivot) {
                ;
            }

            if(leftPtr >= rightPtr) {
                break;
            } else {
                swap(leftPtr, rightPtr);
            }
        }

        swap(leftPtr, right);

        return leftPtr;
    }
}
