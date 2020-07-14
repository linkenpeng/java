package com.intecsec.java.config.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @description:
 * @author: peter.peng
 * @create: 2020-02-05 15:19
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = EnvironmentConfig.class)
@Configuration
@PropertySource("classpath:beanName.properties")
public class EnvironmentConfig {

    @Autowired
    Environment env;

    @Test
    public void testReadProperty() {
        // 获取bean.name.controller 的属性
        System.out.println(env.getProperty("bean.name.controller"));
        // 判断是否包含bean.name.component
        System.out.println(env.containsProperty("bean.name.component"));
        // 返回与给定键关联的属性值
        System.out.println(env.getRequiredProperty("bean.name.service"));
    }
}
