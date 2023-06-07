package com.intecsec.java.basic.lambda;

import java.util.Comparator;

public class LengthAscComparator 
  implements Comparator<String> {
	public int compare(String first, String second) {
		//获取2个字符串长度
		int len1 = (first==null? 0 : first.length());
		int len2 = (first==null? 0 : second.length());
		
		//如果len1 == len2，返回0，表示两个字符串相等
		//如果len1 >  len2，返回正数，表示first>second
		//如果len1 <  len2，返回负数，表示first<second
		return len1 - len2;
	}
}



