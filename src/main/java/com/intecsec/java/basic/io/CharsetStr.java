package com.intecsec.java.basic.io;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-08-31 23:01
 **/
public class CharsetStr {
    public static final String CHARSET = "UTF-8";

    public static void main(String[] args) throws Exception {
        String s = "这是一段中文字符串";
        stringByte(s);
        charsetTest(s);
    }

    @SuppressWarnings("unchecked")
    public static void stringByte(String s) throws Exception {
        byte[] b = s.getBytes(CHARSET);
        System.out.println(b);
        String n = new String(b, CHARSET);
        System.out.println(n);
    }

    public static void charsetTest(String s) {
        Charset charset = Charset.forName(CHARSET);
        ByteBuffer byteBuffer = charset.encode(s);
        System.out.println(byteBuffer.get());
        CharBuffer charBuffer = charset.decode(byteBuffer);
        System.out.println(charBuffer.get());
    }
}
