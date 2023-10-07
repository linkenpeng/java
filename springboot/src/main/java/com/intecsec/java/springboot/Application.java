package com.intecsec.java.springboot;

import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;
import com.intecsec.java.springboot.starter.EnableUserInfoProcessor;
import com.intecsec.java.springboot.starter.UserInfo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author peter.peng
 * @date 2020/7/30
 */
@SpringBootApplication
@EnableUserInfoProcessor
@MapperScan("com.intecsec.java.springboot.mapper")
@NacosPropertySource(dataId = "example", autoRefreshed = true)
public class Application {

	// 优先加载bean 方法一 依赖注入
	@Resource
	private UserInfo userInfo;

	// 优先加载bean 方法二 构造函数
	public Application(UserInfo userInfo) {
		userInfo.print();
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
