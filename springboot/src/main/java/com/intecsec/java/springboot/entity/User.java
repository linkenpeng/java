package com.intecsec.java.springboot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author peter.peng
 * @date 2020/7/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private Integer id;

	private String name;
}
