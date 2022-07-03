package com.intecsec.java.basic.jvm;

import java.util.ArrayList;

/**
 * @description:
 * @author: peter.peng
 * @create: 2022-07-03 17:57
 **/
public class HeapTest {
    byte[] a = new byte[1024 * 100];
    public static void main(String[] args) throws InterruptedException {
        ArrayList<HeapTest> heapTests = new ArrayList<>();
        while (true) {
            heapTests.add(new HeapTest());
            Thread.sleep(5);
        }
    }
}
