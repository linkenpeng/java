package com.intecsec.java.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-09-10 22:45
 **/
@Data
@AllArgsConstructor
public class Student {
    Integer id;
    String name;
}
