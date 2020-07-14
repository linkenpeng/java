package com.intecsec.java.config.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @description:
 * @author: peter.peng
 * @create: 2020-02-05 15:47
 **/
@Configuration
@ImportResource("classpath:importResources.xml")
public class ImportResourceWithConfiguration {

    @Autowired
    private TestService service;

    public void getImportResource(){
        new TestService();
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(ImportResourceWithConfiguration.class);
        context.getBean("testService");

    }
}