package com.intecsec.java.cache.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
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
public class CacheTimeAop {

    @Pointcut("within(com.intecsec.java.cache.service.CacheService)")
    public void pointcut() {}

    /**
     * execution（）	表达式的主体；
     * 第一个”*“符号	表示返回值的类型任意；
     * com.intecsec.java.cache.service.CacheService	AOP所切的类或接口
     * 后面的”..“ 表示当前包及子包
     * 第二个”*“	表示类名下的所有方法，如果是：.*.* 标识的是该包下面的所有类的所有方法。
     * .*(..)	表示任何方法名，括号表示参数，两个点表示任何参数类型
     */
    //@Pointcut("execution(* com.intecsec.java.cache.service.impl.*.*(..))")
    @Pointcut("execution(* com.intecsec.java.cache.service.CacheService.*(..))")
    public void interfaceCut() {}

    /**
     * 识别到所有加了此注解的方法都会被切到
     */
    @Pointcut("@annotation(com.intecsec.java.cache.annotation.CacheTimeLog)")
    public void cacheTimeLogCut() {}


    @After("interfaceCut()")
    public void afterAdvice(JoinPoint joinPoint) {
        // log.info("after method:{}", joinPoint.getSignature().getName());
    }

    @Before("interfaceCut() || cacheTimeLogCut()")
    public void beforeAdvice(JoinPoint joinPoint) {
        // log.info("before method:{}", joinPoint.getSignature().getName());
    }

    @Around("interfaceCut()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        long startTime = System.nanoTime();
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();
        Method method = signature.getMethod();
        try {
            Object object = proceedingJoinPoint.proceed();
            long enTime = System.nanoTime();
            long uTime = (enTime - startTime) / 1000;
            long msTime = uTime / 1000;
            log.info("{} time:{}us, {}ms", method.getName(), uTime, msTime);
            return object;
        } catch (Throwable t) {

        }
        return null;
    }
}
