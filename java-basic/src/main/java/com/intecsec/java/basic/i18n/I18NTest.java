package com.intecsec.java.basic.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @description:
 *
 * java/bin/native2ascii from_file_name to_file_name
 *
 * @author: peter.peng
 * @create: 2023-05-16 07:50
 **/
public class I18NTest {

    public static void main(String[] args) {
        Locale locale = Locale.getDefault();
        System.out.println(Locale.getAvailableLocales());
        System.out.println(locale);
        ResourceBundle bundle = ResourceBundle.getBundle("message", locale);
        String s = bundle.getString("hello");
        System.out.println(s);

        /**
         * 找文件的顺序
         * message_en_US
         * message_en
         * message_zh_CN (默认)
         * message_zh (默认)
         * message
         */
        locale = new Locale("en", "US");
        System.out.println(locale);
        bundle = ResourceBundle.getBundle("message", locale);
        String es = bundle.getString("hello");
        System.out.println(es);

    }

}
