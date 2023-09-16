package com.intecsec.java.cache.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-09-16 11:16
 **/
@Component
@Aspect
@Slf4j
@EnableAspectJAutoProxy
public class CacheTimeAop {

    @Pointcut("within(com.intecsec.java.cache.service.CacheService)")
    private void pointcut() {}

    /**
     * execution（）	表达式的主体；
     * 第一个”*“符号	表示返回值的类型任意；
     * com.intecsec.java.cache.controller.CacheController	AOP所切的服务的包名，即，我们的业务部分
     * 包名后面的”..“	表示当前包及子包
     * 第二个”*“	表示类名，*即所有类。
     * .*(..)	表示任何方法名，括号表示参数，两个点表示任何参数类型
     */
    @Pointcut("execution (* com.intecsec.java.cache.controller.CacheController.*.*(..))")
    private void controllerCut() {}

    @After("controllerCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        log.info("after method:{}", joinPoint.getSignature().getName());
        System.out.println("after method:" + joinPoint.getSignature().getName());
    }

    @Before("controllerCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        log.info("before method:{}", joinPoint.getSignature().getName());
    }

    @Around("controllerCut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.nanoTime();
        log.info("startTime:{}", startTime);

        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        // 获取这个被代理的方法
        Method method = signature.getMethod();
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable t) {

        }

        long enTime = System.nanoTime();
        log.info("method:{} time:{}", method.getName(), enTime - startTime);

        return null;
    }
}
