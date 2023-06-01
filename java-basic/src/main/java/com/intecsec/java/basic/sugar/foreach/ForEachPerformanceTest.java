package com.intecsec.java.basic.sugar.foreach;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;

public class ForEachPerformanceTest {

	public static void main(String[] args) {
		int n = 100000;
		arrayTest(n);
		System.out.println("=====================");
		collectionTest(n);
	}
	
	public static void arrayTest(int n)
	{
		System.out.println("基本类型数组测试");
		double result = 0;
		double[] nums = new double[n];		
		long startTime = 0;
		long endTime = 0;
		
		for(int i=0;i<nums.length;i++)
		{
			nums[i] = Math.random();
		}
		
		//测试for遍历
		startTime = System.nanoTime();
		for(int i=0;i<nums.length;i++)
		{
			result += nums[i];
		}		
		endTime = System.nanoTime();
		System.out.println("for      " + (endTime - startTime));
		
		//测试for-each遍历
		result = 0;
		startTime = System.nanoTime();
		for(double item : nums)
		{
			result += item;
		}
		endTime = System.nanoTime();
		System.out.println("for-each " + (endTime - startTime));
	}
	
	public static void collectionTest(int n)
	{
		System.out.println("集合测试");
		double result = 0;
		HashSet<Double> nums = new HashSet<Double>();		
		long startTime = 0;
		long endTime = 0;
		
		for(int i=0;i<n;i++)
		{
			nums.add(Math.random());
		}
		
		//测试for遍历
		startTime = System.nanoTime();
		for(Iterator<Double> i = nums.iterator(); i.hasNext(); )
		{
			result += i.next();
		}		
		endTime = System.nanoTime();
		System.out.println("for      " + (endTime - startTime));
		
		//测试for-each遍历
		result = 0;
		startTime = System.nanoTime();
		for(double item : nums)
		{
			result += item;
		}
		endTime = System.nanoTime();
		System.out.println("for-each " + (endTime - startTime));
	}

}
