package com.intecsec.java.basic.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 对内存溢出
 *
 * vm args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * 调大年轻代的大小，减少Full GC的次数
 * java -Xms3G -Xmx3G -Xmn2G -Xss1M -XX:MetaspaceSize=512M -XX:MaxMetaspaceSize=512M -jar
 *
 * STW(stop the world), 建设full gc的次数
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
