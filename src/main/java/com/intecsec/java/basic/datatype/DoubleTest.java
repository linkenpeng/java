package com.intecsec.java.basic.datatype;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.intecsec.java.util.PriceUtil;
import org.apache.commons.lang3.ArrayUtils;

public class DoubleTest {
	public static void main(String[] args) {
		t1();
	}

	public static void t2() {
		double d = 0.1;
		double d2 = 0.2;

		System.out.println(d);

		System.out.println(d + d2);
		System.out.println(new BigDecimal(d).add(new BigDecimal(d2)).setScale(1, BigDecimal.ROUND_HALF_UP));


		System.out.println(new BigDecimal(d).doubleValue() == d);
		System.out.println(BigDecimal.valueOf(d).doubleValue() == d);

		bigTest();
	}

	public static void t1() {
		int watsonRatio = 100 - 95;
		double itemWatsonCoupon = 2624 * watsonRatio / 100;
		long itemWatsonCouponAmount = new BigDecimal(2624 * watsonRatio)
				.divide(new BigDecimal(100), 0, BigDecimal.ROUND_FLOOR).longValue();

		double itemWatsonCoupon2 = 376 * watsonRatio / 100;
		long itemWatsonCouponAmount2 = new BigDecimal(376 * watsonRatio)
				.divide(new BigDecimal(100), 0, BigDecimal.ROUND_FLOOR).longValue();

		Map<Long, Long> allocatedCoupon = new HashMap<>();

		System.out.println(itemWatsonCoupon);
		System.out.println(itemWatsonCouponAmount);
		allocatedCoupon.put(1L, itemWatsonCouponAmount);

		System.out.println(itemWatsonCoupon2);
		System.out.println(itemWatsonCouponAmount2);
		allocatedCoupon.put(2L, itemWatsonCouponAmount2);


		// 将浮点小数部分转成整形，才能保持精度
		int scaleBig = 10000;
		int scaleSmall = 100;
		float actualPrice = (float) (-1.31 * scaleBig - 1 / 1 * scaleSmall) / scaleBig;
		float lineAmount =  (float) (-1.31 * scaleBig - 1 * scaleSmall) / scaleBig;

		actualPrice = new BigDecimal(String.valueOf(-1.3199999)).setScale(4, BigDecimal.ROUND_CEILING).floatValue();
		lineAmount = new BigDecimal(String.valueOf(-1.3199999)).setScale(4, BigDecimal.ROUND_HALF_DOWN).floatValue();

		System.out.println(actualPrice);
		System.out.println(lineAmount);

		actualPrice = new BigDecimal(String.valueOf(1.3199999)).setScale(2, BigDecimal.ROUND_FLOOR).floatValue();
		lineAmount = new BigDecimal(String.valueOf(1.3199499)).setScale(4, BigDecimal.ROUND_HALF_DOWN).floatValue();

		System.out.println(actualPrice);
		System.out.println(lineAmount);
	}

	public static void bigTest() {
		double d1 = 11.22;
		double d2 = 33.333;
		double d3 = 16.666;

		BigDecimal b = new BigDecimal(5);
		double x = 20;
		if(x > 10) {
			b = new BigDecimal(10);
		}

		BigDecimal bigDecimal = new BigDecimal(d1).add(b).add(new BigDecimal(d2)).add(new BigDecimal(d3));
		System.out.println(bigDecimal.doubleValue());

	}

	public static void divTest() {
		String dstr = "136.48";
		Double ddbl = Double.parseDouble(dstr);

		int j = 0;
		for(int i = 1; i <= 100; i++) {
			float num= divDouble2Float(ddbl, i, 2);
			if(!String.valueOf(num * i).equals(String.valueOf(ddbl))) {
				System.out.print(i + " ");
				System.out.print(ddbl + " ");
				System.out.print(num + " ");
				System.out.print((num * i) + " ");
				System.out.println("");
				j++;
			}
		}

		System.out.println(j);

	}

	public static float divDouble2Float(double d, int div, int scale) {
		String str = new BigDecimal(d).divide(new BigDecimal(div), scale + 1, BigDecimal.ROUND_UP).toString();
		return Float.parseFloat(str.substring(0, str.length() - 1));
	}

	public static void bigDecimal() {
		BigDecimal b1 = new BigDecimal(String.valueOf(1.3456888f));
		float d1 = 12.4442111111111111111f;
		float d11 = 13.499999f; // 临界点 6位，后5位
		double d12 = 13.599999999999999; // 临界点15位，后14位
		double d13 = 13.500000000000000; // 临界点15位，后14位
		long d2 = 4952;
		long d3 = 3;
		long d4 = 456;
		double d5 = d1 + d2;

		BigDecimal bd = new BigDecimal(d13);
		System.out.println(PriceUtil.fenToYuanFloor(bd));
		System.out.println(PriceUtil.fenToYuanRound(d13));
		System.out.println(PriceUtil.fenToYuanFloat(d2));

		int[] intArray = { 1, 2, 3, 4, 5 };
		String intArrayString = Arrays.toString(intArray);
		System.out.println(intArrayString);

		int[] filterStatus = {40, 50, 60};
		String status = "50";
		if(Arrays.toString(filterStatus).contains(status)) {
			System.out.println(status);
		}

		if(ArrayUtils.contains(filterStatus, 50)) {
			System.out.println(true);
		}
	}

	public static float fenToYuanFloor(BigDecimal from) {
		double df = from.doubleValue();
		return (float)Math.floor(df) / 100.0F;
	}

	public static double m1(double f) {
		BigDecimal bg = new BigDecimal(f/100);
		Float f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
		System.out.println(f1);
		return f1;
	}
	
	// 分转换为元Float
	public static float fenToYuanFloat(Long from) {
		return fenToYuanFloat(from, 2);
	}
	
	public static float fenToYuanFloat(Long from, Integer point) {
		double origin = (double) (from / 100);
		System.out.println(from);
		BigDecimal bg = new BigDecimal(origin);
		Float to = bg.setScale(point, BigDecimal.ROUND_HALF_UP).floatValue();
		return to;
	}

	/**
	 * DecimalFormat转换最简便
	 * 
	 * @return
	 */
	public static String m2(double f) {
		DecimalFormat df = new DecimalFormat("#.00");
		String result = df.format(f);
		System.out.println(result);
		return result;
	}

	/**
	 * String.format打印最简便
	 */
	public static String m3(double f) {
		String df = String.format("%.2f", f);
		System.out.println(df);
		return df;
	}

	public static String m4(double f) {
		NumberFormat nf = NumberFormat.getNumberInstance();
		nf.setMaximumFractionDigits(2);
		String result = nf.format(f);
		System.out.println(result);
		return result;
	}

	public static double m5(double f) {
		double b = (double) (Math.round(f * 100)) / 100;
		System.out.println(b);
		return b;
	}
}
