package com.intecsec.java.vo;

import lombok.Data;

import java.util.Optional;

/**
 * @author Peter.Peng
 * @date 2021/3/4
 */
@Data
public class Country {
	private String isoCode;

	public Optional<String> getIsoCode() {
		return Optional.ofNullable(isoCode);
	}
}
