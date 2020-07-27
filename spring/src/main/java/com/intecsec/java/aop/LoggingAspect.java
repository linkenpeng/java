package com.intecsec.java.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

// 声明为一个切面  
@Order(2)
@Aspect
@Component
public class LoggingAspect {
	
	// 定义一个放，用于声明切入点表达式，一般滴，该方法中再不需要添入其他的代码
	@Pointcut("execution(* com.mycshop.aop.*.*(int, int))")
	public void  declareJoinPointExpression() {}
	
	// 声明是一个前置通知
	@Before("declareJoinPointExpression()")
	public void beforMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinpoint.getArgs());
		
		System.out.println("The method " + methodName + " begins with " + args);
	}
	
	// 后置通知(无论是否发生异常)
	// 在后置通知中还不能访问目标方法执行的结果
	@After("declareJoinPointExpression()")
	public void afterMethod(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("The method " + methodName + " ends with ");
	}
	
	// 在方法正常结束后执行的代码
	// 返回通知是可以访问到方法的返回值的
	@AfterReturning(value = "declareJoinPointExpression()",
			returning="result")
	public void afterRunning(JoinPoint joinpoint, Object result) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("The method " + methodName + " result is: " + result);
	}
	
	// 在目标方法出现异常时会执行的代码
	// 可以访问到异常对象，且可以指定在出现特定异常时再执行通知代码
	@AfterThrowing(value = "declareJoinPointExpression()",
			throwing="ex")
	public void afterThowing(JoinPoint joinpoint, Exception ex) {
		String methodName = joinpoint.getSignature().getName();
		System.out.println("The method " + methodName + " occurs exception: " + ex);		
	}
	
	/**
	 * 环绕通知
	 * 环绕通知类似于动态代理的全过程
	 * 类型的参数可以决定是否执行目标方法
	 * 且环绕通知必须有返回值，返回值即为目标方法的返回值
	 * @param pjd
	 
	@Around("declareJoinPointExpression()")
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
	*/
}
