package com.intecsec.java.basic.generic.variance;

import java.util.ArrayList;

public class VarianceTest {

	public static void testList() {
		ArrayList<B> list1 = new ArrayList<B>();
		list1.add(new B());
		
		ArrayList<A> list2 = list1;  
		//compile error
		
		ArrayList<? extends A> list3 = new ArrayList<B>();   //协变
		ArrayList<? super B> list4 = new ArrayList<A>();      //逆变

		
	}
	public static void testArray() {
		B[] array1 = new B[1];
		array1[0] = new B();

		A[] array2 = array1;

		try {
			array2[0] = new A(); 
			// compile ok, runtime error
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			array2[0] = new C(); 
			// compile ok, runtime ok
		} catch (Exception ex) {
			ex.printStackTrace();			
		}
	}
	
	public static void testArrayAndList()
	{
		B[] r1 = test(new ArrayList<B>()); //compile error
		A[] r2 = test(new ArrayList<B>()); //compile error
		Object[] r3 = test(new ArrayList<Object>()); //compile error
		
		A[] r4 = test(new ArrayList<A>());
		Object[] r5 = test(new ArrayList<A>());
	}
	
	public static A[] test(ArrayList<A> list)
	{
		return new A[1];
	}
	

}
