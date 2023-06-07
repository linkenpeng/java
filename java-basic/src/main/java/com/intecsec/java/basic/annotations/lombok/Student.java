package com.intecsec.java.basic.annotations.lombok;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
	private String name;
	private int age;
}

