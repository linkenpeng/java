package com.intecsec.java.basic.datastructure.array;

/**
 * @description: 数组封装起来
 * @author: peter.peng
 * @create: 2019-01-17 15:07
 **/
public class ArrayInsObj {
    private Person[] a;
    private int nElems;

    public ArrayInsObj(int max) {
        a = new Person[max];
        nElems = 0;
    }


    public void insertionSort() {
        int out, in;
        for(out = 1; out < nElems; out++) {
            Person temp = a[out];
            in = out;
            while (in > 0 && a[in-1].getLastName().compareTo(temp.getLastName()) > 0) {
                a[in] = a[in-1];
                --in;
            }
            a[in] = temp;
        }
    }


    public Person find(String searchName) {
        int j;
        for(j = 0; j < nElems; j++) {
            if(searchName.equals(a[j].getLastName())) {
                break;
            }
        }
        if(j == nElems) {
            return null;
        } else {
            return a[j];
        }
    }

    public void inseart(String last, String first, int age) {
        a[nElems] = new Person(last, first, age);
        nElems++;
    }

    public boolean delete(String searchName) {
        int j;
        for(j = 0; j < nElems; j++) {
            if(searchName.equals(a[j].getLastName())) {
                break;
            }
        }
        if(j == nElems) {
            return false;
        } else {
            for(int k = j; k < nElems; k++) {
                a[k] = a[k+1];
            }
            nElems--;
            return true;
        }
    }

    public void displayA() {
        int j;
        for(j = 0; j < nElems; j++) {
           a[j].displayPerson();
        }
    }
}
