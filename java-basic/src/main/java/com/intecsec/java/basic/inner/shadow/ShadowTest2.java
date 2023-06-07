package com.intecsec.java.basic.inner.shadow;

public class ShadowTest2 {

    public int x = 0;
    
    public void f1()
    {
    	int x = 20;  //局部内部类无法访问得到！
    	class FirstLevel {
            public int x = 1;

            void methodInFirstLevel(int x) {
                System.out.println("x = " + x); //第12行
                System.out.println("this.x = " + this.x); // 第10行
                System.out.println("ShadowTest.this.x = " + ShadowTest2.this.x); //第4行
            }
        }
    	
    	FirstLevel obj = new FirstLevel();
    	obj.methodInFirstLevel(10);
    	
    }   

    public static void main(String... args) {
        ShadowTest2 st = new ShadowTest2();
        st.f1();;
    }
}