package com.intecsec.java.basic.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 运行时常量池溢出
 *
 * jdk1.8 已经废弃永久代内存设置
 * VM Args: -XX:PermSize=10M -XX:MaxPermSize=10M
 *
 * 改用：元数据空间
 * VM Args: -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 *
 * @author: peter.peng
 * @create: 2019-11-24 20:39
 **/
public class RuntimeContantPoolOOM {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();

        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());
        }
    }

}
