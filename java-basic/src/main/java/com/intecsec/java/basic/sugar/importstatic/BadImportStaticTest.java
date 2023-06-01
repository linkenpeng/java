package com.intecsec.java.basic.sugar.importstatic;
import static java.lang.Double.*;
import static java.lang.Math.*;
import static java.lang.Integer.*;
import static java.text.NumberFormat.*;

import java.text.NumberFormat;

public class BadImportStaticTest {

	public static void main(String[] args) {
		double s = PI * pow(parseDouble(args[0]),2);
		NumberFormat nf = getInstance();
		nf.setMaximumFractionDigits(parseInt(args[1]));
		System.out.println("圆面积是：" + nf.format(s));
		//System.out.println("最大值：" + MAX_VALUE); //error
		System.out.println("最大值：" + Integer.MAX_VALUE);
	}
}



