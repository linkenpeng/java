package com.intecsec.java.springboot.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author peter.peng
 * @date 2020/9/25
 */
@Data
public class Blog {
	private Long id;
	private String title;
	private String content;
	private Date gmtCreated;
	private Date gmtModified;
}
