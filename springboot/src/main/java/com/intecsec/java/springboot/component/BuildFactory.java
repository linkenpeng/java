package com.intecsec.java.springboot.component;

import com.intecsec.java.springboot.annotation.MyAopAnno;
import com.intecsec.java.springboot.entity.Student;
import com.sun.corba.se.pept.encoding.InputObject;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-09-10 22:44
 **/
@Component
public class BuildFactory implements InitializingBean, ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    private BeanFactory beanFactory;

    @Autowired
    private List<Builder> builders;

    private Map<States, Builder> statesStateMachineMap = new ConcurrentHashMap<>();

    public BuildFactory() {
        System.out.println("constructor" + ":" + builders);
    }

    @PostConstruct
    public void postConstructMethod() {
        System.out.println("postConstruct" + ":" + builders);
    }

    public void run(States state, Events event) throws Exception{
        Builder builder = statesStateMachineMap.get(state);
        //StateMachine<States, Events> stateMachine = builder.build(state, beanFactory);
        //stateMachine.sendEvent(event);
    }

    public  StateMachine<States, Events> create(States state) throws Exception{
        Builder builder = statesStateMachineMap.get(state);
        //StateMachine<States, Events> stateMachine = builder.build(state, beanFactory);
        //stateMachine.getExtendedState().getVariables().put(InputObject.class, new Student(1, "haha"));
        return null;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        statesStateMachineMap = builders.stream().collect(Collectors.toMap(Builder::getName, Function.identity()));
        System.out.println("afterPropertiesSet" + ":" + builders);
    }

    public void testAop(String a) {
        System.out.println(a + "testAop");
    }
    public void test2Aop(String a) {
        System.out.println(a + "testAop2");
    }

    public void testAround(String a) {
        System.out.println("run");
    }

    @MyAopAnno
    public void testAopAnno(Student student) {
        System.out.println(student.getId() + "-" + student.getName());
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("onApplicationEvent" + ":" + builders);
    }
}
