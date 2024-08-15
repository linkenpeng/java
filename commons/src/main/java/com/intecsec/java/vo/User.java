package com.intecsec.java.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Optional;

/**
 * @author Peter.Peng
 * @date 2021/2/19
 */
@Data
@AllArgsConstructor
public class User implements Serializable {
	private String uid;
	private String devId;
	private String nickName;
	private String email;

	private Address address;

	public Optional<Address> getAddress() {
		return Optional.ofNullable(address);
	}
}
