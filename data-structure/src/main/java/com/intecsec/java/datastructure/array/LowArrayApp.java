package com.intecsec.java.datastructure.array;

/**
 * @description: LowArray测试
 * @author: peter.peng
 * @create: 2019-01-17 15:10
 **/
public class LowArrayApp {

    public static void main(String[] args) {
        LowArray arr;
        arr = new LowArray(100);

        int nElems;
        int j;

        arr.setElem(0, 77);
        arr.setElem(1, 99);
        arr.setElem(2, 44);
        arr.setElem(3, 55);
        arr.setElem(4, 22);
        arr.setElem(5, 88);
        arr.setElem(6, 11);
        arr.setElem(7, 00);
        arr.setElem(8, 66);
        arr.setElem(9, 33);
        nElems = 10;

        for(j = 0; j < nElems; j++) {
            System.out.print(arr.getElem(j) + " ");
        }
        System.out.println("");

        // search
        int searchKey = 26;
        for(j = 0; j < nElems; j++) {
            if(searchKey == arr.getElem(j)) {
                break;
            }
        }
        if(j == nElems) {
            System.out.println("Can't find:" + searchKey);
        } else {
            System.out.println("Fond: " + searchKey);
        }

        // del
        searchKey = 55;
        for(j = 0; j < nElems; j++) {
            if(searchKey == arr.getElem(j)) {
                System.out.println("Delete: " + searchKey);
                break;
            }
        }
        for(int k = j; k < nElems; k++) {
            arr.setElem(k, arr.getElem(k + 1));
        }

        for(j = 0; j < nElems; j++) {
            System.out.print(arr.getElem(j) + " ");
        }
        System.out.println("");
    }
}
