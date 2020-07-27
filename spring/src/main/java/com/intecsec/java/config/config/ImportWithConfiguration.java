package com.intecsec.java.config.config;

import com.intecsec.java.config.pojo.CustomerBo;
import com.intecsec.java.config.pojo.SchedulerBo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @description:
 * @author: peter.peng
 * @create: 2020-02-05 15:37
 **/
public class ImportWithConfiguration {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        CustomerBo customerBo = (CustomerBo) context.getBean("testCustomerBo");
        customerBo.printMsg("System out println('get from customerBo')");

        SchedulerBo schedulerBo = (SchedulerBo) context.getBean("testSchedulerBo");
        schedulerBo.printMsg("System out println('get from schedulerBo')");
    }
}
