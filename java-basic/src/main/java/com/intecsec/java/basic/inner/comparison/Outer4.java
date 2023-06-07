package com.intecsec.java.basic.inner.comparison;
public class Outer4 {
	private String outField1 = "outer 111";
	private static String outStaticField2 = "outer static 222";

	// 静态嵌套类
	//private static class Inner1 {
	//static class Inner1 {
	//protected static class Inner1 {
	protected static class Inner4 {
		//静态嵌套类可以定义静态和非静态成员
		private String innField1 = "inner 333";
		static String innStaticField2 = "inner static 444";
		
		public void innFun1()
		{
			//静态嵌套类可以直接访问包围类的静态成员
			System.out.println(innField1);
			//System.out.println(Outer1.outField1);  //error
			System.out.println(Outer4.outStaticField2);
			
			//静态嵌套类可以通过对象访问包围类的非静态成员
			Outer4 outObj = new Outer4();
			System.out.println(outObj.getOutField1());
		}
		
		public String getInnField1()
		{
			return this.innField1;
		}
		public static String getInnStaticField2()
		{
			hello();
			//Outer1.hello();
			return innStaticField2;
		}
		public static void hello()
		{
			System.out.println("inner hello");
		}
	}
	
	public String getOutField1()
	{
		return this.outField1;
	}
	
	public static void outFun2()
	{
		Inner4 obj1 = new Inner4();
		Inner4 obj2 = new Inner4();
		System.out.println(obj1 == obj2);
		System.out.println(obj1.getInnField1());
		System.out.println(Inner4.getInnStaticField2());
	}
	
	public static void hello()
	{
		System.out.println("outer hello");
	}
}

