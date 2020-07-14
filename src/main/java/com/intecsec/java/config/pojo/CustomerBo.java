package com.intecsec.java.config.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: peter.peng
 * @create: 2020-02-05 15:33
 **/
@Configuration
public class CustomerBo {

    public void printMsg(String msg){
        System.out.println("CustomerBo : " + msg);
    }

    @Bean
    public CustomerBo testCustomerBo(){
        return new CustomerBo();
    }
}
