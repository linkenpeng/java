package com.intecsec.java.basic.sugar.newswitch;

public class SwitchTest {

	public static void main(String[] args) {
		
		System.out.println(judgeMonthDay("Feb"));
		//System.out.println(judgeMonthDay12("June"));
	}
	
	public static int judgeMonthDay(String month)
	{
		int result = 0;
		
		switch(month)
		{
		case "Jan": case "Mar": case "May": case "July": 
		case "Aug": case "Oct": case "Dec": 
			result = 31;
			break;
		case "Apr": case "June": case "Sep": case "Nov": 
			result = 30;
			break;
		case "Feb":
			result = 28;
			break;
		default: 
			result = -1;
		}
		
		return result;
	}

	/*
	public static int judgeMonthDay12(String month)
	{
		//this method works based on Java 12.
		//modify project properties/Java Compiler/Enable preview features
		int result = 0;
		
		//new switch, don't need break clauses
		//-> 之后 : expression/block/throw   
		switch(month)
		{
			case "Jan","Mar","May","July","Aug","Oct","Dec" -> result = 31;			
			case "Apr","June","Sep","Nov" -> result = 30;
			case "Feb" -> result = 28;
			default -> result = -1;
		}
		
		return result;
	}
	 */
	
	public static void testByte()
	{
		Byte a = 100;
		switch(a)
		{
		case 100: 
			System.out.println("100");
		    break;
		case 127: 
			System.out.println("127");
		    break;
		default:
			System.out.println("0");
		}
	}
	
	public static void testCharacter()
	{
		Character a = 100;
		switch(a)
		{
		case 100: 
			System.out.println("100");
		    break;
		case 127: 
			System.out.println("127");
		    break;
		default:
			System.out.println("0");
		}
	}
	
	public static void testShort()
	{
		Short a = 1000;
		switch(a)
		{
		case 100: 
			System.out.println("100");
		    break;
		case 1000: 
			System.out.println("1000");
		    break;
		default:
			System.out.println("0");
		}
	}

	public static void testInteger()
	{
		Integer a = 1000;
		switch(a)
		{
		case 100: 
			System.out.println("100");
		    break;
		case 1000: 
			System.out.println("1000");
		    break;
		default:
			System.out.println("0");
		}
	}
	
//	public static void testLong()
//	{
//		Long a = 1000L;
//		switch(a)
//		{
//		case 100: 
//			System.out.println("100");
//		    break;
//		case 1000: 
//			System.out.println("1000");
//		    break;
//		default:
//			System.out.println("0");
//		}
//	}
//	
//	public static void testFloat()
//	{
//		Float a = 100.0f;
//		switch(a)
//		{
//		case 100: 
//			System.out.println("100");
//		    break;
//		case 1000: 
//			System.out.println("1000");
//		    break;
//		default:
//			System.out.println("0");
//		}
//	}
//	public static void testDouble()
//	{
//		Double a = 100.0;
//		switch(a)
//		{
//		case 100: 
//			System.out.println("100");
//		    break;
//		case 1000: 
//			System.out.println("1000");
//		    break;
//		default:
//			System.out.println("0");
//		}
//	}

	// JDK12
	/*
	public static void testSwitchReturn()
	{
		int num = 1;
		int days = switch (num) {
		    case 1,3,5,7,8,10,12 -> 31;
		    case 4,6,9,11 -> 30;
		    default -> {
		        int result = 28;
		        break result;  //代码块中break返回结果
		    }
		};
		
		System.out.println(days);
	}
	*/

}
