package com.intecsec.java.basic.vm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 对内存溢出
 *
 * vm args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author: peter.peng
 * @create: 2019-11-24 20:22
 **/
public class HeapOOM {

    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
