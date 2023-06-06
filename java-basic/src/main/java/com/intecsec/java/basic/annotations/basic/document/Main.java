package com.intecsec.java.basic.annotations.basic.document;

public class Main {

	public static void main(String arg[]) {
	      new Main().f1();
	      new Main().f2();
	   }
	   @CommonAnnotation (value="Hello CommonAnnotation")
	   public void f1() {
	      System.out.printf("111111111111");
	   }
	   @DocumentedAnnotation(value="Hello DocumentedAnnotation")
	   public void f2() {
	      System.out.printf("222222222222");
	   }
}
