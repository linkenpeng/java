package com.intecsec.java.config.config;

import com.intecsec.java.config.pojo.MyBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @description:
 * @author: peter.peng
 * @create: 2020-02-05 15:27
 **/
@PropertySource("classpath:beanName.properties")
@Configuration
public class ReadValueFromPropertySource {

    @Value("bean.name.component")
    String beanName;

    @Bean("myTestBean")
    public MyBean myBean() {
        return new MyBean(beanName);
    }

}