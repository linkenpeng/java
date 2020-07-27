package com.intecsec.java.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author peter.peng
 * @date 2020/7/17
 */
@Data
public class Coupon implements Serializable {

	private static final long serialVersionUID = 7966678534408622093L;

	private int id;

	private String name;
}
