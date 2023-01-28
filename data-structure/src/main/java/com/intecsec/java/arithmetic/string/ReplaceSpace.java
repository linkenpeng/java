package com.intecsec.java.arithmetic.string;

/**
 * @description: 替换空格
 * @author: peter.peng
 * @create: 2021-01-31 22:11
 **/
public class ReplaceSpace {

    public static void main(String[] args) {

        StringBuffer sb = new StringBuffer();
        sb.append("I am is peter.");

        System.out.println(sb.toString());
        System.out.println(repalce1(sb));
        System.out.println(repalce2(sb));

    }

    public static String repalce1(StringBuffer str) {
        int length = str.length();
        StringBuffer result = new StringBuffer();

        for(int i = 0; i < length; i++) {
            char b = str.charAt(i);
            if(String.valueOf(b).equals(" ")) {
                result.append("%20");
            } else {
                result.append(b);
            }
        }

        return result.toString();
    }

    public static String repalce2(StringBuffer str) {
        return str.toString().replaceAll("\\s", "%20");
    }

}
