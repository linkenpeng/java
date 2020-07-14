package com.intecsec.java.basic.io;

import java.io.*;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-08-31 22:37
 **/
public class Charset {
    public static final String charset = "GBK";

    public static void main(String[] args) throws Exception {
        String file = "/Users/pengzhenxian/watsons/superq/superq-admin/src/main/resources/superq-admin.properties";
        //write(file);
        read(file);
    }

    public static void write(String file) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
        writer.write("这是要保存的中文字符");
        writer.close();
        outputStream.close();
    }

    public static void read(String file) throws Exception {
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream, charset);
        StringBuffer buffer = new StringBuffer();
        char[] buf = new char[64];
        int count = 0;
        while ((count = reader.read(buf)) != -1) {
            buffer.append(buf, 0, count);
        }
        System.out.println(buffer.toString());
        reader.close();
        inputStream.close();
    }
}
