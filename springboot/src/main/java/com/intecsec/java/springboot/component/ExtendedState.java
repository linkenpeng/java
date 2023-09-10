package com.intecsec.java.springboot.component;

import com.intecsec.java.springboot.entity.Student;
import com.sun.corba.se.pept.encoding.InputObject;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: peter.peng
 * @create: 2023-09-10 22:59
 **/
@Data
public class ExtendedState {
    Map<InputObject, Student> variables = new HashMap<>();
}
