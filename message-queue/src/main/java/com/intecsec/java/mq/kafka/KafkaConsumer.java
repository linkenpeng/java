package com.intecsec.java.mq.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;
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

	@KafkaListener(id = "consumer1", groupId = "peter-group", topicPartitions = {
			@TopicPartition(topic = "topic1", partitions = {"0"}),
			@TopicPartition(topic = "topic2", partitions = {"0"}, partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "8"))
	})
	public void onMessage2(ConsumerRecord<?, ?> record) {
		System.out.println("topic:"+record.topic()+"|partition:"+record.partition()+"|offset:"+record.offset()+"|value:"+record.value());
	}

	@KafkaListener(id = "consumer2", groupId = "peter.group", topics = "topic1")
	public void onMessage3(List<ConsumerRecord<?, ?>> records) {
		System.out.println(">>> 批量消费一次，records.size() = " + records.size());
		for (ConsumerRecord<?, ?> record : records) {
			System.out.println(record.value());
		}
	}

	@KafkaListener(topics = "topic1", errorHandler = "consumerAwareListenerErrorHandler")
	public void onMessage4(ConsumerRecord<?, ?> record) throws Exception {
		throw new Exception("简单消费-模拟消费");
	}

	@KafkaListener(topics = "topic1", errorHandler = "consumerAwareListenerErrorHandler")
	public void onMessage5(List<ConsumerRecord<?, ?>> records) throws Exception {
		System.out.println("批量消费一次...");
		throw new Exception("批量消费-模拟消费");
	}

	@KafkaListener(topics = "topic3", containerFactory = "filterContainerFactory")
	public void onMessage6(ConsumerRecord<?, ?> record) throws Exception {
		System.out.println(record.value());
	}

	@KafkaListener(topics = "topic3", containerFactory = "filterContainerFactory")
	@SendTo("topic2")
	public void onMessage7(ConsumerRecord<?, ?> record) throws Exception {
		System.out.println("消息转发: " + record.value() + "-forward message");
	}

}
