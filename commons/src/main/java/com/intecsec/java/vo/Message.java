package com.intecsec.java.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author Peter.Peng
 * @date 2021/3/2
 */
@Data
public class Message {
	private String id;
	private String message;
	private Date sendTime;
}
