package com.intecsec.java.basic.sugar.variablearguments;

public class VariableArgumentTest {

	public static void main(String[] args) {
		print();
		print("aaa");
		print("aaa", "bbb");
		print("aaa", "bbb", "ccc");

	}
	
	public static void print(String... args) {
        System.out.println(args.length);
        for (String arg : args) {
            System.out.println(arg);
        }
    }
	
	//当只有一个参数时，本方法优先级更高
	public static void print(String s)
	{
		 System.out.println("I am another method");
	}
	
	//错误：一个方法不可以有多个可变参数
//	public static void print(String... args, int... irgs) 
//	{
//        
//  }
	
	//错误：一个调用语句不能同时有2个带可变参数的方法适配
//	public static void print(String s1, String... args)
//	{
//		
//	}

}
