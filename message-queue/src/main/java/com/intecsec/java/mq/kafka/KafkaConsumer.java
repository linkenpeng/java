package com.intecsec.java.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author Peter.Peng
 * @date 2021/3/2
 */
@Component
public class KafkaConsumer {

	@KafkaListener(topics = {"test"})
	public void onMessage(ConsumerRecord<?, ?> consumerRecord) {
		Optional<?> optional = Optional.ofNullable(consumerRecord.value());
		if(optional.isPresent()) {
			Object msg = optional.get();
			System.out.println(consumerRecord);
			System.out.println(msg);
		}
	}

}
