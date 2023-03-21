package com.intecsec.java.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Students implements Serializable {
	private int id;
	private String name;
	private int age;
}
