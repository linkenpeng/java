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

		DefaultMQProducer producer = new DefaultMQProducer("ProducerGroupName");
		producer.setNamesrvAddr("dev.mq.intecsec.com:9876");
		//发送超时时间，默认3000 单位ms
		producer.setSendMsgTimeout(5000);
		producer.start();

		try {
			Message msg = new Message("TestTopic",// topic
					"TestTag",                       	// tag 可以为空，用以简单的筛选。
					RandomStringUtils.random(8),  						// key 可以为空，可用以查询。
					("testBody" + RandomStringUtils.random(8)).getBytes());    	// body ，我常将对象转json再获取byte[] 进行传输。
			SendResult send = producer.send(msg);
			if (send.getSendStatus().equals(SendStatus.SEND_OK)) {
				//发送成功处理
			} else {
				//发送失败处理
			}
		} catch (Exception e) {
			//发送失败处理
			e.printStackTrace();
		}
		//正式环境不要发完就就shutdown，要在应用退出时再shutdown。
		producer.shutdown();

	}
}
