package com.intecsec.java.basic.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.SortedMap;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-08-31 22:37
 **/
public class CharsetTest {
    public static final String CHARSET_UTF_8 = "UTF-8";
    public static final String CHARSET_GBK = "GBK";

    public static void main(String[] args) throws Exception {
        String s = "这是一段中文字符串";
        stringByte(s);
        // charsetTest(s);

        // charsetAll();

        String file = "/tmp/test.txt";
        //write(file, CHARSET_GBK);
        //read(file, CHARSET_UTF_8);
    }

    public static void charsetAll() {
        Charset c = Charset.defaultCharset();
        System.out.println(c.name());

        SortedMap<String, Charset> stringCharsetSortedMap = Charset.availableCharsets();
        System.out.println("支持的所有字符");
        for(Map.Entry<String, Charset> entry : stringCharsetSortedMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    public static void stringByte(String s) throws Exception {
        byte[] b = s.getBytes(CHARSET_UTF_8);
        System.out.println(b);
        String n = new String(b, CHARSET_UTF_8);
        System.out.println(n);
        System.out.println(new String(s.getBytes(CHARSET_GBK), CHARSET_UTF_8));
        System.out.println(new String(s.getBytes(CHARSET_UTF_8), CHARSET_GBK));
    }

    public static void charsetTest(String s) {
        Charset charset = Charset.forName(CHARSET_UTF_8);
        ByteBuffer byteBuffer = charset.encode(s);
        System.out.println(byteBuffer.get());
        CharBuffer charBuffer = charset.decode(byteBuffer);
        System.out.println(charBuffer.get());
    }

    public static void write(String file, String charset) throws Exception {
        FileOutputStream outputStream = new FileOutputStream(file);
        OutputStreamWriter writer = new OutputStreamWriter(outputStream, charset);
        BufferedWriter buffWriter = new BufferedWriter(writer);

        buffWriter.write("这是要保存的中文字符");
        buffWriter.newLine();
        buffWriter.write("O(∩_∩)O哈哈~");

        buffWriter.close();
        writer.close();
        outputStream.close();
    }

    public static void read(String file, String charset) throws Exception {
        FileInputStream inputStream = new FileInputStream(file);
        InputStreamReader reader = new InputStreamReader(inputStream, charset);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer();
        char[] buf = new char[64];
        int count = 0;
        while ((count = bufferedReader.read(buf)) != -1) {
            buffer.append(buf, 0, count);
        }
        System.out.println(buffer);
        bufferedReader.read();
        reader.close();
        inputStream.close();
    }
}
