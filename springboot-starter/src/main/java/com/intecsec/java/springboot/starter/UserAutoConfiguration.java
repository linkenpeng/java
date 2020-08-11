package com.intecsec.java.springboot.starter;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author peter.peng
 * @date 2020/7/30
 */
@Configuration
@EnableConfigurationProperties(UserProperties.class)
public class UserAutoConfiguration {

	@Bean
	public UserInfo getBean(UserProperties userProperties) {
		System.out.println("init UserInfo Bean");
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername(userProperties.getUsername());
		userInfo.setPassword(userProperties.getPassword());
		return userInfo;
	}

}
