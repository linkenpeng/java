package com.intecsec.java.mq.rocketmq.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.rocketmq.client.consumer.DefaultLitePullConsumer;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author Peter.Peng
 * @date 2021/5/6
 */
@Slf4j
public class PullConsumer {

	private static boolean runFlag = true;

	public static void main(String[] args) throws Exception {

		DefaultLitePullConsumer consumer = new DefaultLitePullConsumer("PullConsumerGroupName");
		consumer.setNamesrvAddr("dev.mq.intecsec.com:9876");
		//要消费的topic，可使用tag进行简单过滤
		consumer.subscribe("TestTopic", "*");
		//一次最大消费的条数
		consumer.setPullBatchSize(100);
		//无消息时，最大阻塞时间。默认5000 单位ms
		consumer.setPollTimeoutMillis(5000);
		consumer.start();
		while (runFlag){
			try {
				//拉取消息，无消息时会阻塞
				List<MessageExt> msgs = consumer.poll();
				if (CollectionUtils.isEmpty(msgs)){
					continue;
				}
				//业务处理
				msgs.forEach(msg-> log.info(new String(msg.getBody())));
				//同步消费位置。不执行该方法，应用重启会存在重复消费。
				consumer.commitSync();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		consumer.shutdown();

	}

}
