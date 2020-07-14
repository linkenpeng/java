package com.intecsec.java.basic.datatype;

import com.intecsec.java.util.JsonUtils;
import org.apache.commons.lang3.StringEscapeUtils;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-03-18 18:41
 **/
public class StringGen {
    public static void main(String[] args) {
        String str = "old";
        System.out.println(str);
        replace(str);
        System.out.println(str);
    }

    public static void escape() {
        Map<String, String> addtionalMap = new HashMap<>();
        addtionalMap.put("alipayDiscountAmount", "101");
        String additional = StringEscapeUtils.escapeHtml4(JsonUtils.toJson(addtionalMap));
        System.out.println(additional);
    }

    public static void t() {
        long second = 3720000 / 1000L;
        long hour = second / 3600;
        long minute = second % 3600 / 60;
        String result = MessageFormat.format("<font color=\"\">秒杀结束还剩{0}小时{1}分</font>", hour, minute);
        System.out.println(result);
    }

    public static void replace(String str) {
        str = "replaced";
    }
}
