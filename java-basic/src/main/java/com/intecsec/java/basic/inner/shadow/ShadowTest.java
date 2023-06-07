package com.intecsec.java.basic.inner.shadow;

public class ShadowTest {

    public int x = 0;

    class FirstLevel {

        public int x = 1;

        void methodInFirstLevel(int x) {
            System.out.println("x = " + x); //第10行
            System.out.println("this.x = " + this.x); // 第8行
            System.out.println("ShadowTest.this.x = " + ShadowTest.this.x); //第4行
        }
    }

    public static void main(String... args) {
        ShadowTest st = new ShadowTest();
        FirstLevel fl = st.new FirstLevel();
        fl.methodInFirstLevel(20);
    }
}