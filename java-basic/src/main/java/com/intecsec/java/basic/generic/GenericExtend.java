package com.intecsec.java.basic.generic;

/**
 * @description: 泛型继承及通配符
 * @author: peter.peng
 * @create: 2019-09-27 16:19
 **/
public class GenericExtend {

    public static void main(String[] args) {
        Generic<String> generic = new Generic("111111");
        Generic<Integer> generic1 = new Generic(4444);
        Generic<Float> generic2 = new Generic(55.55);
        Generic<Boolean> generic3 = new Generic(false);
        Generic<Number> generic4 = new Generic(666d);
        Generic<Long> generic5 = new Generic(7770);

        show3(generic1);
        show3(generic2);
        show3(generic4);
        show3(generic5);

        // show4(generic2); not ok
        show4(generic1);
        show4(generic4);
    }

    /**
     * 可以把 ? 看成所有类型的父类
     * @param obj
     */
    public static void show2(Generic<?> obj) {
        System.out.println(obj.getKey());
    }

    /**
     * 只能传入number的子类或者number
     * @param obj
     */
    public static void show3(Generic<? extends Number> obj) {
        System.out.println(obj.getKey());
    }

    /**
     * 只能传入Integer的父类或者Integer
     * @param obj
     */
    public static void show4(Generic<? super Integer> obj) {
        System.out.println(obj.getKey());
    }
}
