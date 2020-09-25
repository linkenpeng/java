package com.intecsec.java.springboot.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author peter.peng
 * @date 2020/9/25
 */
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
}
