package com.intecsec.java.basic.datatype;

import com.intecsec.java.util.JsonUtils;
import com.intecsec.java.vo.Coupon;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: peter.peng
 * @create: 2019-03-18 18:41
 **/
public class StringGen {

    public static void main(String[] args) {
        String username = "银\\\\亿\\格兰郡{}}}}}}\"G3号楼(怒江北街银亿格兰郡G3\n" +
                ")";
        System.out.println(username);
        System.out.println(replaceSpecail(username));


        //language=JSON
        String jsonStr = "{\"name\":\"li\", \"age\":19}";
        System.out.println(jsonStr);

        if (jsonStr == null) {

        }
    }

    public static String replaceSpecail(String str) {
        if(StringUtils.isEmpty(str)) {
            return str;
        }
        return str.replaceAll("(\\r\\n|\\r|\\n|\\n\\r|\\{|}|\"|\'|\\\\)", "");
    }

    public static void replace() {
        String sAll = "http://10.66.3.137:11082/,http://10.66.3.137:11083/";
        String s = "http://10.66.3.137:11082/";
        String newAddress = sAll.replaceAll(s+"[,]?", "");
        System.out.println(newAddress);
    }

    public static void toJson() {
        System.out.println(checkSpecailHandle("MO201121175113551511"));
        Coupon coupon = new Coupon();
        coupon.setId(1);
        coupon.setName("coupon");
        System.out.println(JsonUtils.toJson(coupon));
        System.out.println(coupon);

        String str = "old";
        System.out.println(str);
        replace(str);
        System.out.println(str);
    }

    public static boolean checkSpecailHandle(String oid) {
        List<String> oids = Arrays.asList("MO201121175113551511,MO201101124145591935".split(","));
        return  oids.contains(oid)||Character.isLetter(oid.charAt(oid.length()-1));
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
