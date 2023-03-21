package com.intecsec.java.basic.java8;

/**
 * @description: 函数式接口
 * 只要接口中仅仅包含一个抽象方法，我们就可以将其改写为 Lambda 表达式。
 * 为了保证一个接口明确的被定义为一个函数式接口（Functional Interface），
 * 我们需要为该接口添加注解：@FunctionalInterface。
 * 这样，一旦你添加了第二个抽象方法，编译器会立刻抛出错误提示。
 * @author: peter.peng
 * @create: 2023-03-21 22:06
 **/
@FunctionalInterface
public interface Converter<F, T> {
    T convert(F from);

    default T deft(F from, T target) {
        return target;
    }
}
