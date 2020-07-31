package com.intecsec.java.springboot.aop;

import com.intecsec.java.springboot.annotation.MyLog;
import com.intecsec.java.springboot.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Modifier;

/**
 * @author peter.peng
 * @date 2020/7/31
 */
@Component
@Aspect
public class MyLogAspect {

	@Pointcut("@annotation(com.intecsec.java.springboot.annotation.MyLog)")
	private void pointcut(){}

	@Before("pointcut() && @annotation(logger)")
	public void advice(JoinPoint joinPoint, MyLog logger) {
		System.out.println("注解作用的方法名: " + joinPoint.getSignature().getName());

		System.out.println("所在类的简单类名: " + joinPoint.getSignature().getDeclaringType().getSimpleName());

		System.out.println("所在类的完整类名: " + joinPoint.getSignature().getDeclaringType());

		System.out.println("目标方法的声明类型: " + Modifier.toString(joinPoint.getSignature().getModifiers()));

		System.out.println("["
				+ joinPoint.getSignature().getDeclaringType().getSimpleName()
				+ "][" + joinPoint.getSignature().getName()
				+ "]-日志内容-[" + logger.value() + "]");
	}

	@Around("pointcut() && @annotation(logger)")
	public Object around(ProceedingJoinPoint joinPoint, MyLog logger) {
		System.out.println("["
				+ joinPoint.getSignature().getDeclaringType().getSimpleName()
				+ "][" + joinPoint.getSignature().getName()
				+ "]-日志内容-[" + logger.value() + "]");

		Object result = null;

		Object[] args = joinPoint.getArgs();
		for (int i = 0; i < args.length; i++) {
			if(args[i] instanceof Integer) {
				args[i] = (Integer)args[i] - 1;
				break;
			}
		}

		try {
			result = joinPoint.proceed(args);
		} catch (Throwable throwable) {
			throwable.printStackTrace();
		}

		if(result instanceof User) {
			User user = (User) result;
			user.setId(user.getId() + 1);
			return user;
		}
		return result;
	}

}
