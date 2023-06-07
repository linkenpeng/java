package com.intecsec.java.basic.inner.shadow;

public class ShadowTest3 {

    public int x = 0;
    
    public void f1()
    {
    	int x = 20;  //可以访问得到，没有被遮蔽！
    	class FirstLevel {
            void methodInFirstLevel() {
                System.out.println("x = " + x); //第8行
                System.out.println("ShadowTest.this.x = " + ShadowTest3.this.x); //第4行
            }
        }
    	
    	//x=30;
    	FirstLevel obj = new FirstLevel();
    	obj.methodInFirstLevel();
    	
    }   

    public static void main(String... args) {
        ShadowTest3 st = new ShadowTest3();
        st.f1();;
    }
}