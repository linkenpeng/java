package com.intecsec.java.config.config;

import com.intecsec.java.config.pojo.CustomerBo;
import com.intecsec.java.config.pojo.MyBean;
import com.intecsec.java.config.pojo.SchedulerBo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @description:
 * @author: peter.peng
 * @create: 2020-02-05 15:35
 **/
@Configuration
@Import({CustomerBo.class, SchedulerBo.class})
public class AppConfig {

    @Bean
    public MyBean myBean() {
        return new MyBean();
    }
}