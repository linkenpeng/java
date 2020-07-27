package com.intecsec.java.basic.datatype;

import java.math.BigDecimal;

/**
 * @author peter.peng
 * @date 2019/5/21
 */
public class MathTest {

	public static void main(String[] args) {
		System.out.println(divLong2Float(3L, 100, 2));
	}

	public static float divDouble2Float(double d, int div, int scale) {
		String str = BigDecimal.valueOf(d).divide(new BigDecimal(div), scale + 1, BigDecimal.ROUND_UP).toString();
		return Float.parseFloat(str.substring(0, str.length() - 1));
	}

	public static float divLong2Float(long d, int div, int scale) {
		String str = BigDecimal.valueOf(d).divide(new BigDecimal(div), scale + 1, BigDecimal.ROUND_UP).toString();
		return Float.parseFloat(str.substring(0, str.length() - 1));
	}

	// 分转换为元Float（保留2位小数）
	public static float fenToYuanFloat(Long from) {
		return (float) divFloat(from, 100, 2);
	}

	public static float divFloat(double v1, double v2, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException("The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).floatValue();
	}
}
