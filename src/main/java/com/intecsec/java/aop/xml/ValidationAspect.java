package com.intecsec.java.aop.xml;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;

public class ValidationAspect {

	public void validataArgs(JoinPoint joinpoint) {
		String methodName = joinpoint.getSignature().getName();
		List<Object> args = Arrays.asList(joinpoint.getArgs());

		System.out.println("-->validate: " + methodName + " begins with " + args);
	}
}
