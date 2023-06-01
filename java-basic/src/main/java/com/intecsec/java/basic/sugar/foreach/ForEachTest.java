package com.intecsec.java.basic.sugar.foreach;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ForEachTest {

	public static void main(String[] args) {		
		arrayTraverse();
		collectionTraverse();
		elementDeleteOrReplace();
		forWardOrBackwardTraverse();
		traverseBothArray();
	}
	
	public static void arrayTraverse()
	{
		System.out.println("=========数组遍历测试==========");
		double[] nums = new double[5];
		for(int i=0;i<nums.length;i++)
		{
			nums[i] = Math.random();
		}
		for(double item : nums)
		{
			System.out.println(item);
		}
	}
	public static void collectionTraverse()
	{
		System.out.println("=========Collection遍历测试==========");
		
		Set<String> hs = new HashSet<String>();
		hs.add("I");
		hs.add("Love");
		hs.add("Java");
		
		for(Iterator<String> i = hs.iterator(); i.hasNext(); )
		{
			System.out.println(i.next());
		}
	    
		for(String i:hs)
		{
			System.out.println(i);
		}
	}
	
	public static void elementDeleteOrReplace()
	{
		System.out.println("=========删除或替换元素测试==========");
		
		Set<Person> ps = new HashSet<Person>();
		ps.add(new Person("Jerry", 20));
		ps.add(new Person("Mike", 21));
		ps.add(new Person("Tom", 22));
		
		for(Iterator<Person> i = ps.iterator(); i.hasNext(); )
		{
			Person p = i.next();			
			System.out.println(p.getName() + "," + p.getAge());
			//i.remove();  //删除元素
		}
		for(Person p:ps)
		{
			p.setAge(25);
			p=new Person("William", 30); //并没有修改原始数据
		}
		for(Person p:ps)
		{
			System.out.println(p.getName() + "," + p.getAge());
		}		
	}
	
	public static void forWardOrBackwardTraverse()
	{
		System.out.println("=========数组正反向遍历测试==========");
		double[] nums = new double[5];
		for(int i=0;i<nums.length;i++)
		{
			nums[i] = Math.random();
		}
		
		for(int i=0;i<nums.length;i++)
		{
			System.out.println(nums[i]);
		}
		for(double item : nums)
		{
			System.out.println(item);
		}
		for(int i=nums.length-1;i>=0;i--)
		{
			System.out.println(nums[i]);
		}

		//for-each 只能向前遍历，不支持向后遍历
	}
	
	public static void traverseBothArray()
	{
		System.out.println("=========两个数组遍历测试==========");
		double[] nums1 = new double[5];
		double[] nums2 = new double[5];
		
		for(int i=0;i<nums1.length;i++)
		{
			nums1[i] = Math.random();
			nums2[i] = Math.random();
		}
		
		for(int i=0;i<nums1.length;i++)
		{
			System.out.println(nums1[i] + "," + nums2[i]);
		}
		
		//for-each 不能同时遍历两个数组
	}
}
