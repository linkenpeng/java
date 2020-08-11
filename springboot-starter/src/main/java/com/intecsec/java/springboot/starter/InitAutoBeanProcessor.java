package com.intecsec.java.springboot.starter;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;

/**
 * 方法四，优先于程序内bean的初始化, 使用此种方法加载的bean，可以不用设置: META-INFO/spring.factories
 * @author peter.peng
 * @date 2020/8/11
 */
public class InitAutoBeanProcessor extends InstantiationAwareBeanPostProcessorAdapter implements BeanFactoryAware {

	private ConfigurableListableBeanFactory beanFactory;

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		if (!(beanFactory instanceof ConfigurableListableBeanFactory)) {
			throw new IllegalArgumentException(
					"AutowiredAnnotationBeanPostProcessor requires a ConfigurableListableBeanFactory: " + beanFactory);
		}
		this.beanFactory = (ConfigurableListableBeanFactory) beanFactory;

		UserInfo userInfo = beanFactory.getBean(UserInfo.class);
		// 通过主动调用beanFactory#getBean来显示实例化目标bean
		System.out.println("get userInfo Bean: " + userInfo);
	}
}
