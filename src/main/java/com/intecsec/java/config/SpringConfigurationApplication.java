package com.intecsec.java.config;

import com.intecsec.java.config.config.MyConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: peter.peng
 * @create: 2020-02-05 11:52
 **/
public class SpringConfigurationApplication {

    public static void main(String[] args) {
        // AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        // 因为我们加载的@Configuration 是基于注解形式的，所以需要创建AnnotationConfigApplicationContext
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        // 注册MyConfiguration 类并刷新bean 容器。
        context.register(MyConfig.class);
        context.refresh();

        for(String beanName: context.getBeanDefinitionNames()) {
            System.out.println("beanName:" + beanName);
        }
        // context.close();

        // ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

//        ApplicationContext context =
//                new AnnotationConfigApplicationContext(ReadValueFromPropertySource.class);
//        MyBean myBean = (MyBean) context.getBean("myTestBean");
//        System.out.println("myBean = " + myBean);
    }
}
