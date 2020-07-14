package com.intecsec.java.config.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: peter.peng
 * @create: 2020-02-05 15:34
 **/
@Configuration
public class SchedulerBo {

    public void printMsg(String msg){
        System.out.println("SchedulerBo : " + msg);
    }

    @Bean
    public SchedulerBo testSchedulerBo(){
        return new SchedulerBo();
    }
}
