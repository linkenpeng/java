package com.intecsec.java.springboot.entity;

import lombok.Data;

/**
 * @author peter.peng
 * @date 2020/9/25
 */
@Data
public class Blog {
	private Long id;
	private String title;
	private String content;
}
