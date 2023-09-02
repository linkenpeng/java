package com.intecsec.java.basic.datatype;

import com.google.common.base.CaseFormat;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.intecsec.java.util.Base64;
import com.intecsec.java.util.JsonUtils;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
// import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.text.StringEscapeUtils;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

	public static void main(String[] args) {
		List<String> currentPromotionIdList = Lists.newArrayList();
		currentPromotionIdList.add("a");
		currentPromotionIdList.add("b");
		currentPromotionIdList.add("c");
		String s = StringUtils.join(currentPromotionIdList, ",");
		System.out.println(s);
	}

	public static void sortFileName() {
		String[] fileNames = {
				"PromotionExport_2023-08-12T000013.xml",
				"PromotionExport_2023-08-13T000013.xml",
				"PromotionExport_2023-08-14T000010.xml",
				"PromotionExport_2023-08-14T020012.xml",
				"PromotionExport_2023-08-15T000014.xml",
				"PromotionExport_2023-08-15T114119.xml",
				"PromotionExport_2023-08-16T000010.xml",
				"PromotionExport_2023-08-17T000011.xml",
				"PromotionExport_2023-08-18T000012.xml",
				"PromotionExport_2023-08-18T120410.xml",
				"PromotionExport_2023-08-19T000010.xml",
				"PromotionExport_2023-08-20T000015.xml",
				"PromotionExport_2023-08-21T000011.xml",
				"PromotionExport_2023-08-22T000011.xml",
		};
		System.out.println(JsonUtils.toJson(fileNames));
		Arrays.sort(fileNames);
		System.out.println(JsonUtils.toJson(fileNames));
	}

	public static void ioInput() {
		List<String> list = Lists.newArrayList("a","b","c","d"," ", "f");
		String str2 = StringUtils.join(list, ",");
		InputStream inputStream = IOUtils.toInputStream(str2, Charsets.toCharset("UTF-8"));
		System.setIn(inputStream);
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter(",");
		while (scanner.hasNext()) {
			System.out.println(scanner.next());
		}
	}

	public static void caseName() {
		String str1 = "NUMS_ITEM";
		String str2 = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str1);
		System.out.println(str2);
		String str3 = CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, str1);
		System.out.println(str3);
	}

	public static void escape() {
		String str = "You can't \"escape\"";
		String escapeStr = StringEscapeUtils.escapeJava(str);
		System.out.println(escapeStr);
		String unEscapeStr = StringEscapeUtils.unescapeJava(str);
		System.out.println(unEscapeStr);
	}

	public static void split() {
		String itemCodes = "101162924 ";
		List<String> itemCodeStrList = Splitter.on(",").splitToList(itemCodes);
		List<Long> itemCodeList = Lists.newArrayList();
		for(String itemCodeStr : itemCodeStrList) {
			itemCodeList.add(Long.valueOf(itemCodeStr.trim()));
		}
		System.out.println(itemCodeList);
	}

	public static void string2list() {
		List<String> list = Lists.newArrayList("a","b","c","d","f");

		String str1 = String.join(",");
		System.out.println("str1:" + str1);
		String str2 = StringUtils.join(list, ",");
		System.out.println("str2:" + str2);

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i));
			if (i < list.size() - 1) {
				sb.append(",");
			}
		}
		System.out.println("str3:" + sb.toString());

		/*Iterable<String> split = Splitter.on(",").trimResults().omitEmptyStrings().split(str2);
		System.out.println(split);

		List<String> list2 = Arrays.asList(str2.split(","));
		System.out.println(list2);*/

	}

	public static void replace() {
		String js = "\\\\\\\\\\\\\\\"COUPON\\\\\\\\\\\\\\\",\\\\\\\\\\\\\\\"amount\\\\\\\\\\\\\\\":99";
		System.out.println(js);
		System.out.println(js.replaceAll("\\\\\\\\", ""));
	}

	public static void jsMapStr() {
		String json = "";
		Map<String, String> map = new HashMap<>();
		map.put("abc", json);
		System.out.println(JsonUtils.toJson(map));
		String toJson = JsonUtils.toJson(map);
		Map<String, String> mapResult = JsonUtils.parseJson(toJson, HashMap.class);

		String js = mapResult.get("abc");
		System.out.println(js.length());
		System.out.println(StringUtils.substring(js, 0, 65535));
	}

	public static void base64() {
		String txt = "discountAmt=2&mchtDiscountAmt=1&cDiscountAmt=30";
		try {
			String encode = com.intecsec.java.util.Base64.encode(txt.getBytes("UTF-8"));
			System.out.println(encode);

			byte[] decode = Base64.decode(encode);
			String s = new String(decode);
			/*System.out.println(s);
			String s2 = s.substring(1, s.length() -1);
			System.out.println(s2);*/

			for(int i = 0; i < 10; i++) {
				System.out.println(i);
			}

			Map<String, String> map = parseUrlParam2Map(s);
			System.out.println(JsonUtils.toJson(map));

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	public static Map<String, String> parseUrlParam2Map(String params) {
		Map<String,String> map = new HashMap<>();
		if(StringUtils.isEmpty(params)) {
			return map;
		}

		String[] split = params.split("&");
		for (int i = 0; i < split.length; i++) {
			String[] kv = split[i].split("=");
			if(kv != null) {
				map.put(kv[0], kv.length > 1 ? kv[1] : "");
				System.out.println(map);
			}
		}

		return map;
	}


	public static void bubTest() {
		String str = "abc=123&";
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(str);
		System.out.println(stringBuilder.length());
		System.out.println(stringBuilder.toString().length());
		System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));


		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append(str);
		System.out.println(stringBuffer.length());
		System.out.println(stringBuffer.toString().length());
		System.out.println(stringBuffer.substring(0, stringBuffer.length() - 1));
	}

	public static void regexTest() {
		String str = "merchandise item-[100901008] ";
		Pattern pattern = Pattern.compile("item-\\[(\\d+)\\]");
		Matcher matcher = pattern.matcher(str);
		System.out.println(matcher);
		int count = 0;
		while (matcher.find()) {
			System.out.print("Start index: " + matcher.start());
			System.out.print(" End index: " + matcher.end() + " ");
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
			count++;
		}
		System.out.println(count);

		StringBuffer stringBuffer = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(stringBuffer,"--");
		}
		matcher.appendTail(stringBuffer);
		System.out.println(stringBuffer.toString());
		System.out.println(matcher.replaceAll("--"));

	}

	public static void lenTest() {
		String str = "湘雅路87号 住院部BC区！！到s层（四层与五层之间）进入左手边的工作人员通道";
		//System.out.println(str.length());
		try {
			/*int ULen = str.getBytes("utf-8").length;
			System.out.println(ULen);*/

			String str2 = substringUTF8(str, 300);
			System.out.println(str2);
			int ULen2 = str2.getBytes("utf-8").length;
			System.out.println(ULen2);

		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}


	public static String substring(String text, int length, String encode)
			throws UnsupportedEncodingException {
		String[] encodes = new String[] { "GB2312", "GBK", "GB18030", "CP936",
				"CNS11643", "UTF-8" };
		List<String> encodeList = Arrays.asList(encodes);
		if (text == null) {
			return null;
		}
		if(!encodeList.contains(encode)) {
			return text;
		}
		StringBuilder sb = new StringBuilder();
		int currentLength = 0;
		for (char c : text.toCharArray()) {
			currentLength += String.valueOf(c).getBytes(encode).length;
			if (currentLength <= length) {
				sb.append(c);
			} else {
				break;
			}
		}
		return sb.toString();
	}

	public static String substringUTF8(String text, int length) throws UnsupportedEncodingException {
		return substring(text, length, "UTF-8");
	}

	public static void cutString() {
		String cardNumber = String.valueOf("170524999999999991234");
		StringBuffer card = new StringBuffer(cardNumber);
		//cardNumber = card.replace(card.length() - 4, card.length(), "****").toString();
		System.out.println(cardNumber);

		System.out.println(cardNumber.substring(0, 4));
		System.out.println(cardNumber.substring(cardNumber.length() - 4, cardNumber.length()));

		String newString = String.format("%05d", Integer.parseInt("1236"));
		System.out.println(newString);
	}

	public static void cutOrder() {
		String orderSn = "180211112004576840";
		System.out.println(orderSn.length());
		System.out.println("charAt:" + orderSn.charAt(orderSn.length() - 3));

		String t1 = orderSn.substring(orderSn.length() - 4, orderSn.length()); // 后4位
		System.out.println(t1);
		System.out.println("订单后4位" + t1);

		String tsn = StringUtils.substring(orderSn, orderSn.length() - 4, orderSn.length());
		System.out.println("订单后4位" + tsn);

		String t2 = orderSn.substring(0, 4); // 订单号前4位

		String t3 = orderSn.substring(orderSn.length() - 8, orderSn.length() - 4);
		System.out.println(t3);
	}

	public static void regReplace() {
		String name = "6件装面膜28毫升X1片";
		Pattern pattern = Pattern.compile("\\d+件装[\\|]*");
		Matcher matcher = pattern.matcher(name);
		System.out.println(matcher);
		// 字符串是否与正则表达式相匹配
		boolean rs = matcher.matches();
		System.out.println(rs);

		System.out.println(name.replaceAll("^\\d+件装[\\|]*", ""));

		StringBuffer stringBuffer = new StringBuffer();
	}

	public static  void  formart() {
		String parttern = "format:{0}:{1}:{2}:{3}:{4}";
		String formt = MessageFormat.format(parttern, "a", "b", "c", "d", "e");
		System.out.println(formt);
	}

	public static  void  contains() {
		String p = "MOT";
		String t = "MOT123";
		System.out.println(t.contains(p));
	}

	public static  void replaceEnd() {
		String s = "abdcd,";
		s = StringUtils.stripEnd(s,",");
		System.out.println(s);
	}

	public static void compare() {
		String string = "170524999999999999999";
		String string2 = "17052500000000000000";
		// 返回1表示大于，返回0表示相等，返回-1表示小于
		System.out.println(string2.compareTo(string));
		//		long l1 = Long.parseLong(string);
		//		long l2 = Long.parseLong(string2);
		//		System.out.println(l2 > l1);

		//java.lang.NumberFormatException: For input string: "17052411203496072101"
	}

}
