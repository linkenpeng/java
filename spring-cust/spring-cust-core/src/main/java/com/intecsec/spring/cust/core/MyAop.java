package com.intecsec.spring.cust.core;

import java.lang.reflect.Method;

public class MyAop {
	private final String target;
	private final String targetMethod;
	private final Object aspect;
	private final AdviceEnum advice;
	private final Method method;
	public MyAop(String target, String targetMethod, Object aspect, AdviceEnum advice, Method method) {
		super();
		this.target = target;
		this.targetMethod = targetMethod;
		this.aspect = aspect;
		this.advice = advice;
		this.method = method;
	}
	
	public String getTarget() {
		return target;
	}
	public String getTargetMethod() {
		return targetMethod;
	}
	public Object getAspect() {
		return aspect;
	}
	public AdviceEnum getAdvice() {
		return advice;
	}
	public Method getMethod() {
		return method;
	}
	
	
}
