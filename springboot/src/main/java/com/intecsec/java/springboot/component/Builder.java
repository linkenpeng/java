package com.intecsec.java.springboot.component;

import lombok.Data;
import org.springframework.beans.factory.BeanFactory;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-09-10 22:52
 **/
@Data
public class Builder {
    private States name;

    private void build(States state, BeanFactory beanFactory) {

    }
}
