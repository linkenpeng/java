package com.intecsec.java.springboot.starter;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author peter.peng
 * @date 2020/7/30
 */
@ConfigurationProperties(prefix = "com.intecsec.java")
public class UserProperties {
	private String username;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
