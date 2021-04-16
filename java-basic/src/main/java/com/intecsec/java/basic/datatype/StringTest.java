package com.intecsec.java.basic.datatype;

import com.intecsec.java.util.Base64;
import com.intecsec.java.util.JsonUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {

	public static void main(String[] args) {
		jsMapStr();
	}

	public static void jsMapStr() {
		String json = "{\"orderId\":\"596679600\",\"watsonsOrderId\":null,\"createTime\":\"2021-04-15 12:13:35\",\"orderType\":\"CND\",\"statusCode\":\"RECEIVE\",\"channel\":\"MYSTORE\",\"orderNo\":\"80621041520494\",\"optType\":\"T005\",\"optCode\":\"YR21041512132992309\",\"optStatus\":true,\"changedAmt\":-16.59,\"message\":null,\"changedItems\":[{\"uuiTid\":\"302919875\",\"itemNo\":\"100997609\",\"orderqty\":0,\"status\":0,\"itemAmount\":null,\"remark\":null},{\"uuiTid\":\"302919874\",\"itemNo\":\"101141910\",\"orderqty\":0,\"status\":0,\"itemAmount\":null,\"remark\":null}],\"data\":{\"id\":596679600,\"appointEndTime\":\"2021-04-15 13:06:44\",\"appointStartTime\":\"2021-04-15 12:06:44\",\"buyerMemo\":null,\"channelCode\":\"MYSTORE\",\"tlogSalesChannel\":\"16\",\"createDate\":\"2021-04-15 12:07:45\",\"deliveryChannel\":\"FD\",\"financeStore\":\"3\",\"gomsOrderNo\":null,\"linkedOrderCode\":null,\"location\":\"000\",\"needInvoice\":null,\"orderAmt\":0.0,\"orderBarcode\":null,\"orderBarcodeUrl\":null,\"orderDate\":\"2021-04-15 12:02:18\",\"orderFrom\":\"MYSTORE\",\"orderNo\":\"80621041520494\",\"orderType\":\"CND\",\"posID\":\"160\",\"priceBarcode\":null,\"priceBarcodeUrl\":null,\"replenishment\":null,\"salesSource\":\"MYSTORE\",\"scanCodeFlag\":null,\"sendEmailStatus\":0,\"status\":\"RECEIVE\",\"storeId\":\"806\",\"syncFlag\":null,\"ticket\":null,\"tid\":\"80621041520494\",\"transporterCode\":null,\"updateDate\":\"2021-04-15 12:07:45\",\"watsonsOrderNo\":null,\"discount\":15.01,\"version\":2,\"warehouseNo\":\"806\",\"wlName\":\"FD\",\"compositiveOrderNo\":null,\"prescription\":0,\"mallNo\":\"8998\",\"unpaidAmount\":0.0,\"isDemolition\":null,\"oid\":\"MYSTORE80621041520494\",\"fullReturn\":0,\"isCancelExce\":0,\"exchangeOrderNo\":null,\"exchangeType\":null,\"channelSource\":\"MYSTORE\",\"parentOrderNo\":null,\"salesChannel\":null,\"items\":[{\"id\":8616680240,\"actualPrice\":1.0,\"barCode\":null,\"itemAmount\":0.0,\"itemFlag\":\"N\",\"itemNameZht\":\"屈臣氏门店自提购物袋\",\"itemNo\":\"100997609\",\"itemSequenceNo\":\"302919875\",\"itemweight\":0.0,\"memberPrice\":0.0,\"memberSaving\":null,\"onlinePrice\":0.0,\"orderId\":596679600,\"orderqty\":0,\"outOfStock\":null,\"productionCode\":null,\"productionDate\":null,\"productSequence\":null,\"recordId\":null,\"referalCode\":\"50572940\",\"returnSupport\":null,\"type\":null,\"uuiTid\":\"302919875\",\"parentUnitId\":\"302919875\",\"promoParentUnitId\":\"302919875\",\"createDate\":\"2021-04-15 12:13:35\",\"promotionId\":null,\"promotionDesc\":null,\"promotionSequence\":null,\"couponCode\":null,\"isEarnPoints\":null,\"earnPoints\":null,\"subAccountId\":null,\"origeinPrice\":1.0,\"mixedSku\":null,\"version\":2},{\"id\":8616680340,\"actualPrice\":3.4,\"barCode\":null,\"itemAmount\":0.0,\"itemFlag\":\"N\",\"itemNameZht\":\"又萌麻辣开心果45克\",\"itemNo\":\"101141910\",\"itemSequenceNo\":\"302919874\",\"itemweight\":0.0,\"memberPrice\":0.0,\"memberSaving\":null,\"onlinePrice\":0.0,\"orderId\":596679600,\"orderqty\":0,\"outOfStock\":null,\"productionCode\":null,\"productionDate\":null,\"productSequence\":null,\"recordId\":null,\"referalCode\":\"50572940\",\"returnSupport\":null,\"type\":null,\"uuiTid\":\"302919874\",\"parentUnitId\":\"302919874\",\"promoParentUnitId\":\"302919874\",\"createDate\":\"2021-04-15 12:13:35\",\"promotionId\":null,\"promotionDesc\":null,\"promotionSequence\":null,\"couponCode\":null,\"isEarnPoints\":null,\"earnPoints\":null,\"subAccountId\":null,\"origeinPrice\":3.4,\"mixedSku\":null,\"version\":2},{\"id\":8616680440,\"actualPrice\":0.0,\"barCode\":null,\"itemAmount\":0.0,\"itemFlag\":\"PP\",\"itemNameZht\":\"又萌麻辣开心果45克\",\"itemNo\":\"101141910\",\"itemSequenceNo\":\"3\",\"itemweight\":0.0,\"memberPrice\":0.0,\"memberSaving\":null,\"onlinePrice\":0.0,\"orderId\":596679600,\"orderqty\":0,\"outOfStock\":null,\"productionCode\":null,\"productionDate\":null,\"productSequence\":null,\"recordId\":null,\"referalCode\":\"50572940\",\"returnSupport\":null,\"type\":null,\"uuiTid\":\"3\",\"parentUnitId\":\"302919874\",\"promoParentUnitId\":\"302919874\",\"createDate\":\"2021-04-15 12:13:35\",\"promotionId\":\"9000000\",\"promotionDesc\":\"屈臣氏卡累计积分\",\"promotionSequence\":null,\"couponCode\":null,\"isEarnPoints\":\"Y\",\"earnPoints\":1,\"subAccountId\":\"1000\",\"origeinPrice\":3.4,\"mixedSku\":null,\"version\":2},{\"id\":8616680540,\"actualPrice\":-1.11,\"barCode\":null,\"itemAmount\":0.0,\"itemFlag\":\"AP\",\"itemNameZht\":\"又萌麻辣开心果45克\",\"itemNo\":\"101141910\",\"itemSequenceNo\":\"4\",\"itemweight\":0.0,\"memberPrice\":0.0,\"memberSaving\":null,\"onlinePrice\":0.0,\"orderId\":596679600,\"orderqty\":0,\"outOfStock\":null,\"productionCode\":null,\"productionDate\":null,\"productSequence\":null,\"recordId\":null,\"referalCode\":\"50572940\",\"returnSupport\":null,\"type\":null,\"uuiTid\":\"4\",\"parentUnitId\":\"302919874\",\"promoParentUnitId\":\"302919874\",\"createDate\":\"2021-04-15 12:13:35\",\"promotionId\":\"23317\",\"promotionDesc\":\"满￥10.02凭券减￥10.01\",\"promotionSequence\":null,\"couponCode\":null,\"isEarnPoints\":null,\"earnPoints\":null,\"subAccountId\":null,\"origeinPrice\":3.4,\"mixedSku\":null,\"version\":2},{\"id\":8616680640,\"actualPrice\":-0.56,\"barCode\":null,\"itemAmount\":0.0,\"itemFlag\":\"AP\",\"itemNameZht\":\"又萌麻辣开心果45克\",\"itemNo\":\"101141910\",\"itemSequenceNo\":\"5\",\"itemweight\":0.0,\"memberPrice\":0.0,\"memberSaving\":null,\"onlinePrice\":0.0,\"orderId\":596679600,\"orderqty\":0,\"outOfStock\":null,\"productionCode\":null,\"productionDate\":null,\"productSequence\":null,\"recordId\":null,\"referalCode\":\"50572940\",\"returnSupport\":null,\"type\":null,\"uuiTid\":\"5\",\"parentUnitId\":\"302919874\",\"promoParentUnitId\":\"302919874\",\"createDate\":\"2021-04-15 12:13:35\",\"promotionId\":\"9001\",\"promotionDesc\":\"POINT REDEMPTION\",\"promotionSequence\":null,\"couponCode\":null,\"isEarnPoints\":null,\"earnPoints\":null,\"subAccountId\":null,\"origeinPrice\":3.4,\"mixedSku\":null,\"version\":2},{\"id\":8616680740,\"actualPrice\":0.0,\"barCode\":null,\"itemAmount\":0.0,\"itemFlag\":\"PP\",\"itemNameZht\":\"又萌麻辣开心果45克\",\"itemNo\":\"101141910\",\"itemSequenceNo\":\"6\",\"itemweight\":0.0,\"memberPrice\":0.0,\"memberSaving\":null,\"onlinePrice\":0.0,\"orderId\":596679600,\"orderqty\":0,\"outOfStock\":null,\"productionCode\":null,\"productionDate\":null,\"productSequence\":null,\"recordId\":null,\"referalCode\":\"50572940\",\"returnSupport\":null,\"type\":null,\"uuiTid\":\"6\",\"parentUnitId\":\"302919874\",\"promoParentUnitId\":\"302919874\",\"createDate\":\"2021-04-15 12:13:35\",\"promotionId\":\"2013850\",\"promotionDesc\":\"黑卡会员双倍积分\",\"promotionSequence\":null,\"couponCode\":null,\"isEarnPoints\":\"Y\",\"earnPoints\":1,\"subAccountId\":\"1000\",\"origeinPrice\":3.4,\"mixedSku\":null,\"version\":2},{\"id\":8616680840,\"actualPrice\":0.0,\"barCode\":null,\"itemAmount\":0.0,\"itemFlag\":\"D\",\"itemNameZht\":\"邮费\",\"itemNo\":\"100115700\",\"itemSequenceNo\":\"7\",\"itemweight\":0.0,\"memberPrice\":0.0,\"memberSaving\":null,\"onlinePrice\":0.0,\"orderId\":596679600,\"orderqty\":0,\"outOfStock\":null,\"productionCode\":null,\"productionDate\":null,\"productSequence\":null,\"recordId\":null,\"referalCode\":null,\"returnSupport\":null,\"type\":null,\"uuiTid\":\"7\",\"parentUnitId\":\"7\",\"promoParentUnitId\":\"7\",\"createDate\":\"2021-04-15 12:13:35\",\"promotionId\":null,\"promotionDesc\":null,\"promotionSequence\":null,\"couponCode\":null,\"isEarnPoints\":null,\"earnPoints\":null,\"subAccountId\":null,\"origeinPrice\":0.0,\"mixedSku\":null,\"version\":2}],\"shipping\":{\"id\":602464187,\"shipCity\":\"成都市\",\"shipProvince\":\"四川省\",\"shipArea\":\"武侯区\",\"shipAddress\":\"SOHO沸城B座(科华北路沸城屈臣氏60号)\",\"shipName\":\"刘春蓉\",\"shipMobile\":\"13540898128\",\"shipTel\":\"13540898128\",\"shipPostCode\":null,\"orderId\":596679600,\"createDate\":\"2021-04-15 12:07:45\",\"lat\":30.624043,\"lng\":104.075493,\"coordinateType\":3,\"version\":1},\"user\":{\"id\":535107047,\"crmNo\":\"11063929496\",\"buyerNick\":\"13540898128\",\"cardNo\":\"11063929496\",\"fullCardNo\":null,\"orderId\":596679600,\"memberId\":null,\"openId\":null,\"point\":null,\"redemption\":0.0,\"employeePoint\":0.0,\"employeeRedemPtion\":0.0,\"createDate\":\"2021-04-15 12:07:45\",\"version\":1,\"outUserId\":\"oWHdYwZ3Hiqvk_w68RC4xOEcI6Dk\",\"outUserChannel\":\"WeChat\"},\"payment\":{\"id\":609880902,\"payStatus\":\"1\",\"confimStatus\":\"0\",\"payTime\":\"2021-04-15 12:06:44\",\"payment\":\"P012\",\"memo\":\"微信\",\"payFee\":0.0,\"paymentTransactionId\":\"80621041520494\",\"paymentAlipayNo\":null,\"orderId\":596679600,\"createDate\":\"2021-04-15 12:07:45\",\"version\":2,\"paymentDetail\":null},\"express\":null,\"expressDetail\":null,\"invoice\":null,\"orderStatusLog\":null,\"orderHandle\":null,\"orderStoreChannelInfo\":{\"id\":517763486,\"orderId\":596679600,\"createDate\":\"2021-04-15 12:07:45\",\"tlogWarehouseNo\":null,\"tlogStoreId\":\"8998\",\"cashierId\":null,\"referralCode\":\"50572940\",\"referralStore\":\"806\",\"tlogTicketId\":\"9395\",\"allotStore\":null,\"version\":1},\"points\":[{\"subAccountId\":\"1000\",\"earnPoints\":2,\"redeemPoints\":100,\"version\":1}],\"demolition\":null,\"demolitionResult\":null},\"paymentDetail\":[]}";
		Map<String, String> map = new HashMap<>();
		map.put("WCLOUD_ORDER_STATUS_MESSAGE_QUEUE", json);
		System.out.println(JsonUtils.toJson(map));
		String toJson = JsonUtils.toJson(map);
		Map<String, String> mapResult = JsonUtils.parseJson(toJson, HashMap.class);

		String js = mapResult.get("WCLOUD_ORDER_STATUS_MESSAGE_QUEUE");
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
		while (matcher.find()) {
			System.out.print("Start index: " + matcher.start());
			System.out.print(" End index: " + matcher.end() + " ");
			System.out.println(matcher.group());
			System.out.println(matcher.group(1));
		}
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

		String t2 = orderSn.substring(0, 4); // 订单号前4位
		System.out.println(t2);

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
