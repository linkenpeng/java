package com.intecsec.java.mq.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ConsumerAwareListenerErrorHandler;

/**
 * @author Peter.Peng
 * @date 2021/3/2
 */
@Configuration
public class KafkaInitialConfiguration {

	@Autowired
	ConsumerFactory consumerFactory;

	@Bean
	public NewTopic initialTopic() {
		return new NewTopic("topic3", 8, (short) 2 );
	}

	@Bean
	public NewTopic updateTopic() {
		return new NewTopic("topic3", 10, (short) 2 );
	}

	@Bean
	public ConsumerAwareListenerErrorHandler consumerAwareListenerErrorHandler() {
		return (message, exception, consumer) -> {
			System.out.println("消费异常" + message.getPayload());
			return null;
		};
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory filterContainerFactory() {
		ConcurrentKafkaListenerContainerFactory factory = new ConcurrentKafkaListenerContainerFactory();
		factory.setConsumerFactory(consumerFactory);
		factory.setAckDiscarded(true);
		factory.setRecordFilterStrategy(consumerRecord -> {
			if(Integer.parseInt(consumerRecord.value().toString()) % 2 == 0) {
				return false;
			}
			return true;
		});
		return factory;
	}
}
