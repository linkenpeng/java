package com.intecsec.java.basic.sugar.enumtype;

public class DayTest {

	public static void main(String[] args) {
		Day d1 = Day.MONDAY;
		Day d2 = Enum.valueOf(Day.class, "MONDAY");
		System.out.println(d1 == d2);    //true
		
		Day d3 = Enum.valueOf(Day.class, "TUESDAY");
		System.out.println(d1.compareTo(d3)); //MONDAY<TUESDAY
		
		//遍历所有的枚举值
		for(Day item : Day.values())
		{
			System.out.println(item.toString() + "," + item.ordinal());
		}		
	}
}

enum Day
{
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;
}
