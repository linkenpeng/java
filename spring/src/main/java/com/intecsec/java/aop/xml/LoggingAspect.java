package com.intecsec.java.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.List;

public class LoggingAspect {
	
	public void beforMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		
		System.out.println("The method " + methodName + " begins with " + args);
	}
	
	public void afterMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends with ");
	}
	
	public void afterRunning(JoinPoint joinpoint, Object result) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("The method " + methodName + " result is: " + result);
	}
	
	public void afterThowing(JoinPoint joinpoint, Exception ex) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("The method " + methodName + " occurs exception: " + ex);		
	}
	
	public Object around(ProceedingJoinPoint pjd) {
		Object result = null;
		String methodName = pjd.getSignature().getName();
		try {
			// 前置通知
			System.out.println("The method " + methodName + " begin with " + Arrays.asList(pjd.getArgs()));
			result = pjd.proceed();
			// 后置通知
			System.out.println("The method " + methodName + " result " + result);
		} catch (Throwable e) {
			// 异常通知
			System.out.println("The method " + methodName + " occurs exception: " + e);		
			e.printStackTrace();
			
			throw new RuntimeException(e);
		}
		// 后置通知
		System.out.println("The method " + methodName + " ends with ");		
		
		return result;
	}
}
