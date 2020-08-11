package com.intecsec.java.springboot.starter;

/**
 * @author peter.peng
 * @date 2020/7/29
 */
public class UserInfo {

	private String username;

	private String password;

	public void print() {
		System.out.println("username="+username + " password="+password);
	}

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
