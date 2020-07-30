package com.intecsec.java.springboot.starter;

import com.intecsec.java.service.UserService;
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
	public UserService getBean(UserProperties userProperties) {
		UserService userService = new UserService();
		userService.setUsername(userProperties.getUsername());
		userService.setPassword(userProperties.getPassword());
		return userService;
	}

}
