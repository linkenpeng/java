package com.intecsec.java.springboot;

import com.intecsec.java.springboot.starter.EnableUserInfoProcessor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author peter.peng
 * @date 2020/7/30
 */
@SpringBootApplication
@EnableUserInfoProcessor
@MapperScan("com.intecsec.java.springboot.mapper")
public class Application {

	/*// 优先加载bean 方法一 依赖注入
	@Autowired
	private UserInfo userInfo;

	// 优先加载bean 方法二 构造函数
	public Application(UserInfo userInfo) {
		userInfo.print();
	}*/

	public static void main(String[] args) {
		System.out.println("run application");
		SpringApplication.run(Application.class, args);
	}
}
