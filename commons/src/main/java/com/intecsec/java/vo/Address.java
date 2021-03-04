package com.intecsec.java.vo;

import lombok.Data;

import java.util.Optional;

/**
 * @author Peter.Peng
 * @date 2021/3/4
 */
@Data
public class Address {
	private Country country;

	public Optional<Country> getCountry() {
		return Optional.ofNullable(country);
	}
}
