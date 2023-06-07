package com.intecsec.java.basic.lambda;

public class RunnableTest {

	public static void main(String[] args) {
		
		new Thread(
			()->
				{
					int sum=0;
					for(int i=1;i<=100;i++)
					{
						sum = sum + i;
					}
					System.out.println("总和:" + sum);
				}
			).start();

	}

}
