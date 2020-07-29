package com.intecsec.java.util;

import java.math.BigDecimal;

public class PriceUtil {
	//默认除法运算精度  
    private static final int DEF_DIV_SCALE = 10;  
	
	// 分转换为元Double
	public static double fenToYuanDouble(Long from) {
		return div(from, 100, 2);
	}
	
	// 分转换为元Float
	public static float fenToYuanFloat(Long from) {
		return (float) divFloat(from, 100, 2);
	}
	
	// 舍去法分转换为元
	public static float fenToYuanFloor(BigDecimal from) {
		double df = from.doubleValue();
		return (float) Math.floor(df) / 100;
	}
	
	// 四舍五入法分转换为元
	public static float fenToYuanRound(Double from) {
		return (float) divFloat(from, 100, 2);
	}
	
	public static float bigToFloat(BigDecimal bg) {
		return (float) bg.floatValue();
	}

	public static float fenToYuanFloor4Point(BigDecimal from) {
		double df = from.doubleValue();
		return divDouble2Float(df, 100, 4);
	}

	public static float divDouble2Float(double d, int div, int scale) {
		String str = BigDecimal.valueOf(d).divide(new BigDecimal(div), scale + 1, 0).toString();
		return Float.parseFloat(str.substring(0, str.length() - 1));
	}
  
    /** 
     * 提供精确的加法运算。 
     * @param v1 被加数 
     * @param v2 加数 
     * @return 两个参数的和 
     */  
    public static double add(double v1,double v2){  
        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
        return b1.add(b2).doubleValue();  
    }  
    /** 
     * 提供精确的减法运算。 
     * @param v1 被减数 
     * @param v2 减数 
     * @return 两个参数的差 
     */  
    public static double sub(double v1,double v2){  
        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
        return b1.subtract(b2).doubleValue();  
    }  
    /** 
     * 提供精确的乘法运算。 
     * @param v1 被乘数 
     * @param v2 乘数 
     * @return 两个参数的积 
     */  
    public static double mul(double v1,double v2){  
        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
        return b1.multiply(b2).doubleValue();  
    }  
  
    /** 
     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 
     * 小数点以后10位，以后的数字四舍五入。 
     * @param v1 被除数 
     * @param v2 除数 
     * @return 两个参数的商 
     */  
    public static double div(double v1,double v2){  
        return div(v1,v2,DEF_DIV_SCALE);  
    }  
  
    /** 
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 
     * 定精度，以后的数字四舍五入。 
     * @param v1 被除数 
     * @param v2 除数 
     * @param scale 表示表示需要精确到小数点以后几位。 
     * @return 两个参数的商 
     */  
    public static double div(double v1,double v2,int scale){  
        if(scale<0){  
            throw new IllegalArgumentException(  
                "The scale must be a positive integer or zero");  
        }  
        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).doubleValue();  
    }  
    
    public static float divFloat(double v1,double v2,int scale){  
        if(scale<0){  
            throw new IllegalArgumentException(  
                "The scale must be a positive integer or zero");  
        }  
        BigDecimal b1 = new BigDecimal(Double.toString(v1));  
        BigDecimal b2 = new BigDecimal(Double.toString(v2));  
        return b1.divide(b2,scale,BigDecimal.ROUND_HALF_UP).floatValue();  
    }  
  
    /** 
     * 提供精确的小数位四舍五入处理。 
     * @param v 需要四舍五入的数字 
     * @param scale 小数点后保留几位 
     * @return 四舍五入后的结果 
     */  
    public static double round(double v,int scale){  
  
        if(scale<0){  
            throw new IllegalArgumentException(  
                "The scale must be a positive integer or zero");  
        }  
        BigDecimal b = new BigDecimal(Double.toString(v));  
        BigDecimal one = new BigDecimal("1");  
        return b.divide(one,scale,BigDecimal.ROUND_HALF_UP).doubleValue();  
    }
}
