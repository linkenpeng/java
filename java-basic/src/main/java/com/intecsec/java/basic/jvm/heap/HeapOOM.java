package com.intecsec.java.basic.jvm.heap;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 堆内存溢出
 *
 * 共享，内存大户，存储所有的对象和数组
 * -Xms 初始堆值，-Xmx最大堆值
 *
 * vm args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 *
 * Exception:java.lang.OutOfMemoryError
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
