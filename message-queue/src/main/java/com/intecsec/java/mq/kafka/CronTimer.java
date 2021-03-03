package com.intecsec.java.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Peter.Peng
 * @date 2021/3/2
 */
@EnableScheduling
@Component
public class CronTimer {

	@Autowired
	private KafkaListenerEndpointRegistry registry;

	@Autowired
	private ConsumerFactory consumerFactory;

	@Bean
	public ConcurrentKafkaListenerContainerFactory delayContainerFactory() {
		ConcurrentKafkaListenerContainerFactory container = new ConcurrentKafkaListenerContainerFactory();
		container.setConsumerFactory(consumerFactory);
		//禁止KafkaListener自启动
		container.setAutoStartup(false);
		return container;
	}

	// 监听器
	@KafkaListener(id="timingConsumer",topics = "topic1",containerFactory = "delayContainerFactory")
	public void onMessage1(ConsumerRecord<?, ?> record){
		System.out.println("消费成功："+record.topic()+"-"+record.partition()+"-"+record.value());
	}

	// 定时启动监听器
	@Scheduled(cron = "0 * * * * ? ")
	public void startListener() {
		System.out.println("启动监听器...");
		if (!registry.getListenerContainer("timingConsumer").isRunning()) {
			registry.getListenerContainer("timingConsumer").start();
		}
	}

	@Scheduled(cron = "5 * * * * ? ")
	public void shutDownListener() {
		System.out.println("关闭监听器...");
		registry.getListenerContainer("timingConsumer").pause();
	}
}
