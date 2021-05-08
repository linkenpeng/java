package com.intecsec.java.mq.rocketmq.producer;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author Peter.Peng
 * @date 2021/5/8
 */
public class ProducerBatch {

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

			int threadCount = 20;
			int forCount = 1000;
			CountDownLatch latch = new CountDownLatch(threadCount);
			long start = System.currentTimeMillis();
			for (int i = 0; i < threadCount; i++) {
				new Thread(() -> {
					try {
						List<Message> list = new ArrayList<>();
						for (int j = 0; j < forCount; j++) {
							try {
								String topic = "TestTopic";
								String tags = "TestTag";
								String keys = j + "_" + RandomStringUtils.random(8, true, true);
								String body = ("testBody:" + j);
								Message msg = new Message(topic, tags, keys, body.getBytes());
								list.add(msg);
								if (list.size() >= 100) {
									SendResult send = producer.send(list);
									if (send.getSendStatus().equals(SendStatus.SEND_OK)) {
										//发送成功处理
										list.clear();
									}else {
										//发送失败处理
									}
								}
							} catch (Exception e) {
								//发送失败处理
								e.printStackTrace();
							}
						}
						if (list.size() > 0) {
							SendResult send = producer.send(list);
							if (!send.getSendStatus().equals(SendStatus.SEND_OK)) {
								System.out.println(send);
							}
							list.clear();
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						latch.countDown();
					}
				}).start();
			}
			latch.await();
			long hs = System.currentTimeMillis() - start;
			System.out.println("耗时：" + hs);

			double speed = (threadCount * forCount) / (hs < 1000 ? 1 : hs / 1000);
			System.out.println("速度 : " + speed);
			//正式环境不要发完就就shutdown，要在应用退出时再shutdown。
			producer.shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
