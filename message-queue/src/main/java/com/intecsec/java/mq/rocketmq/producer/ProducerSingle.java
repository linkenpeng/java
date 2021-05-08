package com.intecsec.java.mq.rocketmq.producer;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

/**
 * @author Peter.Peng
 * @date 2021/5/8
 */
public class ProducerSingle {

	public static void main(String[] args) throws Exception {
		sendMq();
	}

	public static void sendMq() {
		try {
			DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
			producer.setNamesrvAddr("dev.mq.intecsec.com:9876");
			//发送超时时间，默认3000 单位ms
			producer.setSendMsgTimeout(5000);

			producer.start();

			String topic = "TestTopic";
			String tags = "TestTag";
			String keys = RandomStringUtils.random(8, true, true);
			String body = ("testBody:" + RandomStringUtils.random(8, true, true));
			Message msg = new Message(topic, tags, keys, body.getBytes());
			SendResult send = producer.send(msg);

			if (send.getSendStatus().equals(SendStatus.SEND_OK)) {
				//发送成功处理
			} else {
				//发送失败处理
			}

			//正式环境不要发完就就shutdown，要在应用退出时再shutdown。
			producer.shutdown();
		} catch (Exception e) {
			//发送失败处理
			e.printStackTrace();
		}
	}
}
