package com.intecsec.java.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Peter.Peng
 * @date 2021/2/19
 */
@Data
public class User implements Serializable {
	private String uid;
	private String devId;
	private String nickName;
}
