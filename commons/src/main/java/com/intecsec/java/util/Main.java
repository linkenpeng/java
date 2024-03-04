package com.intecsec.java.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import org.apache.http.NameValuePair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Peter.Peng
 * Created on 2023/2/1 16:21
 */
public class Main {

    public static void main(String[] args) {
        huTool();
    }

    public static void huTool() {
        // https://cloud.tencent.com/developer/article/2301087
        // 1、转换为字符串
        int a = 1;
        // aStr为"1"
        String aStr = Convert.toStr(a);
        long[] b = {1,2,3,4,5};
        // bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);

        // 2、转换为指定类型数组
        String[] bb = { "1", "2", "3", "4" };
        // 结果为Integer数组
        Integer[] intArray = Convert.toIntArray(bb);

        long[] c = {1,2,3,4,5};
        // 结果为Integer数组
        Integer[] intArray2 = Convert.toIntArray(c);

        // 3、转换为日期对象
        String aa = "2017-05-06";
        Date value = Convert.toDate(aa);

        // 4、转换为集合
        Object[] ac = {"a", "你", "好", "", 1};
        List<?> list = Convert.convert(List.class, ac);
        //从4.1.11开始可以这么用
        List<?> list1 = Convert.toList(ac);

        // 5、半角和全角转换
        // 半角转全角
        String a2 = "123456789";
        // 结果为："１２３４５６７８９"
        String sbc = Convert.toSBC(a2);

        // 全角转半角
        String a3 = "１２３４５６７８９";
        // 结果为"123456789"
        String dbc = Convert.toDBC(a3);

        // 6、Unicode和字符串转换
        String a4 = "我是一个小小的可爱的字符串";
        String unicode = Convert.strToUnicode(a4);
        // 结果为："我是一个小小的可爱的字符串"
        String raw = Convert.unicodeToStr(unicode);

        // 7、编码转换
        String a5 = "我不是乱码";
        // 转换后result为乱码
        String result = Convert.convertCharset(a5, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        String raw1 = Convert.convertCharset(result, CharsetUtil.ISO_8859_1, "UTF-8");

        // 8、金额大小写转换
        double a6 = 67556.32;
        // 结果为："陆万柒仟伍佰伍拾陆元叁角贰分"
        String digitUppercase = Convert.digitToChinese(a6);
        // System.out.println(digitUppercase);

        // 9、数字转换
        // 数字转为英文表达
        // ONE HUNDRED AND CENTS TWENTY THREE ONLY
        String format = Convert.numberToWord(100.23);

        // 数字简化
        // 1.2k
        String format1 = Convert.numberToSimple(1200);

        // 数字转中文
        // 数字转中文方法中，只保留两位小数
        // 一万零八百八十九点七二
        String f1 = Convert.numberToChinese(10889.72356, false);

        // 使用金额大写
        // 壹万贰仟陆佰伍拾叁
        String f2 = Convert.numberToChinese(12653, true);

        // 数字中文表示转换为数字
        // 1012
        String f3 = Convert.chineseMoneyToNumber("一千零一十二").toString();

        // 10、原始类和包装类转换
        // 去包装
        Class<?> wrapClass = Integer.class;
        // 结果为：int
        Class<?> unWraped = Convert.unWrap(wrapClass);
        // 包装
        Class<?> primitiveClass = long.class;
        // 结果为：Long.class
        Class<?> wraped = Convert.wrap(primitiveClass);
        System.out.println(unWraped);
        System.out.println(wraped);
    }

    public static void get() {
        List<NameValuePair> params = new ArrayList<>();
        String response = HttpUtil.get("http://localhost:8085/tid/get", params, "abc");
        System.out.println("response:" + response);
    }
}
