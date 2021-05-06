package com.intecsec.java.mq.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.protocol.heartbeat.MessageModel;

/**
 * @author Peter.Peng
 * @date 2021/5/6
 */
@Slf4j
public class PushConsumer {

	public static void main(String[] args)  throws Exception {

		DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("PushConsumerGroupName");
		consumer.setNamesrvAddr("127.0.0.1:9876");
		//一个GroupName第一次消费时的位置
		consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_LAST_OFFSET);
		consumer.setConsumeThreadMin(20);
		consumer.setConsumeThreadMax(20);
		//要消费的topic，可使用tag进行简单过滤
		consumer.subscribe("TestTopic", "*");
		//一次最大消费的条数
		consumer.setConsumeMessageBatchMaxSize(100);
		//消费模式，广播或者集群，默认集群。
		consumer.setMessageModel(MessageModel.CLUSTERING);
		//在同一jvm中 需要启动两个同一GroupName的情况需要这个参数不一样。
		consumer.setInstanceName("InstanceName");
		//配置消息监听
		consumer.registerMessageListener((MessageListenerConcurrently) (msgs, context) -> {
			try {
				//业务处理
				msgs.forEach(msg -> {
					log.info(msg.toString());
					log.info(msg.toString());
				});
			} catch (Exception e) {
				System.err.println("接收异常" + e);
				return ConsumeConcurrentlyStatus.RECONSUME_LATER;
			}
			return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
		});
		consumer.start();
		System.out.println("Consumer Started.");

	}

}
