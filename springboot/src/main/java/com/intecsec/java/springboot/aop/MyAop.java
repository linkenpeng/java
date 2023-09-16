package com.intecsec.java.springboot.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

@Slf4j
public class MyAop {

    // 切点定义
    /**
     * 方法执行时触发
     * execution (* com.aop.controller.*.*(..))
     * 方法类型包含Public，Protected等，可省略
     * 方法返回值类型，*可以包含所有的返回值类型
     * 包路径，如"com.intecsec…*",表示"com.demo"包以及该包之下子包的所有类型
     * 方法名称，如“add*”,表示所有以add开头的方法，参数：(*)表示任意一个参数，(…)表示所有参数
     * 异常类型，如execution(* *(…) throws Exception)”匹配所有抛出Exception的方法。
     */
    @Pointcut("execution (* com.intecsec.java.springboot.controller.BlogController.*.*(..))")
    public void pointCut1() {

    }
    /**
     * 带有指定注解的方法触发
     */
    @Pointcut("@annotation(com.intecsec.java.springboot.annotation.MyLog)")
    public void pointCut2() {

    }

    /**
     * 通过参数
     * args() 匹配不带参数的方法
     * args(java.lang.String) 匹配方法参数是String类型的
     * args(…) 带任意参数的方法
     * args(java.lang.String,…) 匹配第一个参数是String类型的，其他参数任意。最后一个参数是String的同理。
     */
    @Pointcut("args(java.lang.String)")
    public void pointCut3() {

    }

    /**
     * 目标对象使用aop之后生成的代理对象必须是指定的类型才会被拦截，注意是目标对象被代理之后生成的代理对象和指定的类型匹配才会被拦截
     * <p>
     * 若spring创建的对象如果实现了接口，默认使用jdk动态代理，如果没有实现接口，使用cglib创建代理对象
     * <p>
     * 所以 service 是使用jdk动态代理生成的对象，service instanceof ServiceImpl 为 false
     * <p>
     * - @Pointcut("this(com.aop.service.impl.ServiceImpl)")
     * 表示被spring代理之后生成的对象必须为com.aop.service.impl.ServiceImpl才会被拦截，
     * 但是service不是ServiceImpl类型的对象了，所以不会被拦截
     * <p>
     */
    @Pointcut("this(com.intecsec.java.springboot.service.BlogService)")
    public void pointCut4() {

    }

    /**
     * this作用于代理对象，target作用于目标对象
     * this表示目标对象被代理之后生成的代理对象和指定的类型匹配会被拦截，匹配的是代理对象
     * target表示目标对象和指定的类型匹配会被拦截，匹配的是目标对象
     */
    @Pointcut("target(com.intecsec.java.springboot.service.BlogService)")
    public void pointCut5() {

    }


    /**
     * within是用来指定类型的，指定类型中的所有方法将被拦截
     * <p>
     * within(com.demo.service.impl.UserServiceImpl) 匹配UserServiceImpl类对应对象的所有方法调用
     * 并且只能是UserServiceImpl对象，不能是它的子对象

     * 拦截包中任意方法，不包含子包中的方法
     * within(com.xyz.service.*)
     * 拦截service包及子包中任意类的任意方法
     * within(com.xyz.service..*)
     */
    @Pointcut("within(com.intecsec.java.springboot.service.BlogService)")
    public void pointCut6() {

    }

    /**
     * 匹配的目标对象的类有一个指定的注解
     * 目标对象中包含com.aop.annotion.ListReturnCheck注解，调用该目标对象的任意方法都会被拦截
     */
    @Pointcut("@target(com.intecsec.java.springboot.annotation.MyAopAnno)")
    public void pointCut7() {

    }

    /**
     * 指定匹配必须包含某个注解的类里的所有连接点
     * 目标对象中包含com.aop.annotion.ListReturnCheck注解的类中的所有方法都会被拦截
     * <p>
     * - @target 和 @within 的不同点
     * - @target(注解A)：判断被调用的目标对象中是否声明了注解A，如果有，会被拦截
     * - @within(注解A)： 判断被调用的方法所属的类中是否声明了注解A，如果有，会被拦截
     * - @target关注的是被调用的对象，@within关注的是调用的方法所在的类
     */
    @Pointcut("@within(com.intecsec.java.springboot.annotation.MyLog)")
    public void pointCut8() {

    }

    /**
     * 方法参数所属的类型上有指定的注解，被匹配
     * 注意：是方法参数所属的类型上有指定的注解，不是方法参数中有注解
     * <p>
     *
     * 匹配1个参数，且第1个参数所属的类中有Anno1注解
     * - @args(com.aop.annotion.ListReturnCheck)
     * 匹配多个参数，且多个参数所属的类型上都有指定的注解
     * - @args(com.aop.annotion.ListReturnCheck,com.aop.annotion.ListReturnCheck)
     * 匹配多个参数，且第一个参数所属的类中有Anno1注解
     * - @args(com.zhangqi.aop.annotion.ListReturnCheck,..)
     */
    @Pointcut("@args(com.intecsec.java.springboot.annotation.MyLog)")
    public void pointCut9() {

    }

    /**
     * PointCut中可以使用&&、||、!运算
     */
    @Pointcut("pointCut7() && pointCut8()")
    public void pointCut10() {

    }

    // -----------以上是切点的定义-----------


    // -----------以下是通知定义-----------

    /**
     * 前置通知（@Before）：在目标方法调用之前调用通知
     * argNames 切点的方法的参数名
     */
    @Before(value = "pointCut1()", argNames = "id,username")
    public void beforeAdvice(JoinPoint joinPoint, String id, String username) {
        // 这个RequestContextHolder是Springmvc提供来获得请求的东西
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(requestAttributes)).getRequest();

        // 记录下请求内容
        log.info("请求地址 : " + request.getRequestURL().toString());
        log.info("请求方式 : " + request.getMethod());
        log.info("请求IP : " + request.getRemoteAddr());
        log.info("请求的参数 : " + Arrays.toString(joinPoint.getArgs()));

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        // 获取这个被代理的方法
        Method method = signature.getMethod();
        // 是获取包+类名的
        String declaringTypeName = signature.getDeclaringTypeName();
        // 获取了方法名
        String name = signature.getName();
        // 返回的是需要加强的目标类的对象
        Object target = joinPoint.getTarget();
        // 返回的是经过加强后的代理类的对象
        Object aThis = joinPoint.getThis();

    }

    /**
     * 后置通知（@After）：在目标方法完成之后调用通知
     */
    @After("pointCut1()")
    public void afterAdvice(JoinPoint joinPoint) {
        System.out.println("afterAdvice...");
    }

    /**
     * 环绕通知（@Around）：在目标方法完成之后调用通知
     */
    @Around("pointCut1()")
    public Object aroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
        System.out.println("Around -- before");
        try {
            return proceedingJoinPoint.proceed();
        } catch (Throwable t) {
            return "";
        }
    }

    /**
     * 返回通知（@AfterReturning）：在目标方法成功执行之后调用通知
     *
     * @param result 代理方法执行的结果
     */
    @AfterReturning(returning = "result", pointcut = "pointCut1())")
    public void doAfterReturning(JoinPoint joinPoint, Object result) throws Exception {

    }
    /**
     * 异常通知（@AfterThrowing）：在目标方法出现异常调用通知
     *
     * @param e 异常信息
     */
    @AfterThrowing(throwing = "e", pointcut = "pointCut1())")
    public void doAfterThrowing(JoinPoint joinPoint, Exception e) throws Exception {

    }
}
