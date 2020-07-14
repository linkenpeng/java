package com.intecsec.java.basic.datastructure.array;

/**
 * @description: 面向过程的数组
 * @author: peter.peng
 * @create: 2019-01-17 10:07
 **/
public class ArrayApp {
    public static void main(String[] args) {
        long arr[];
        arr = new long[100];
        int nElems = 0;

        int j;
        long serachKey;

        arr[0] = 77;
        arr[1] = 99;
        arr[2] = 44;
        arr[3] = 55;
        arr[4] = 22;
        arr[5] = 88;
        arr[6] = 11;
        arr[7] = 00;
        arr[8] = 66;
        arr[9] = 33;

        nElems = 10;

        for (j = 0; j < nElems; j++) {
            System.out.print(arr[j] + " ");
        }
        System.out.println("");

        // 查找
        serachKey = 66;
        for(j = 0; j < nElems; j++) {
            if(arr[j] == serachKey) {
                break;
            }
        }
        if(j == nElems) {
            System.out.println("Can't find " + serachKey);
        } else {
            System.out.println("Found " + serachKey);
        }

        serachKey = 55;
        for(j = 0; j < nElems; j++) {
            if(arr[j] == serachKey) {
                System.out.println("Del " + serachKey);
                break;
            }
        }
        for(int k = j; k < nElems; k++) {
            arr[k] = arr[k+1];
        }
        nElems--;

        for (j = 0; j < nElems; j++) {
            System.out.print(arr[j] + " ");
        }
        System.out.println("");
    }
}
