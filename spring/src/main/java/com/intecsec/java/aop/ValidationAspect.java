package com.intecsec.java.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

// 使用Order 使用切面的优先级
@Order(1)
@Aspect
@Component
public class ValidationAspect {

	@Before("LoggingAspect.declareJoinPointExpression()")
	public void validataArgs(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinpoint.getArgs());

		System.out.println("-->validate: " + methodName + " begins with " + args);
	}
}
