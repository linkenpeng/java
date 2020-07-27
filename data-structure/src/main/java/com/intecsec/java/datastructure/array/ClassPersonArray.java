package com.intecsec.java.datastructure.array;

/**
 * @description: 数组封装起来
 * @author: peter.peng
 * @create: 2019-01-17 15:07
 **/
public class ClassPersonArray {
    private Person[] a;
    private int nElems;

    public ClassPersonArray(int max) {
        a = new Person[max];
        nElems = 0;
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
