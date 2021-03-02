package com.intecsec.java.mq.controller;

import com.intecsec.java.util.JsonUtils;
import com.intecsec.java.vo.Message;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.lang.Nullable;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @author Peter.Peng
 * @date 2021/3/2
 */
@RestController
public class KafkaController {

	@Resource
	private KafkaTemplate<String, Object> kafkaTemplate;

	@GetMapping("/kafka/normal/{msg}")
	public void sendMessage(@PathVariable("msg") String msg) {
		Message message = new Message();
		message.setId(UUID.randomUUID().toString());
		message.setSendTime(new Date());
		message.setMessage(msg);
		kafkaTemplate.send("topic1", JsonUtils.toJson(message));
	}

	@GetMapping("/kafka/callbackOne/{msg}")
	public void sendMessage2(@PathVariable("msg") String msg) {
		kafkaTemplate.send("topic1", msg).addCallback(success -> {
			String topic = success.getRecordMetadata().topic();
			int partition = success.getRecordMetadata().partition();
			long offset = success.getRecordMetadata().offset();
			System.out.println("发送消息成功：" + topic + "-" + partition + "-" + offset);
		}, failure -> {
			System.out.println("发送消息失败：" + failure.getMessage());
		});
	}

	@GetMapping("/kafka/callbackTwo/{msg}")
	public void sendMessage3(@PathVariable("msg") String msg) {
		kafkaTemplate.send("topic1", msg).addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
			@Override
			public void onFailure(Throwable throwable) {
				System.out.println("发送消息失败：" + throwable.getMessage());
			}

			@Override
			public void onSuccess(@Nullable SendResult<String, Object> stringObjectSendResult) {
				String topic = stringObjectSendResult.getRecordMetadata().topic();
				int partition = stringObjectSendResult.getRecordMetadata().partition();
				long offset = stringObjectSendResult.getRecordMetadata().offset();
				System.out.println("发送消息成功：" + topic + "-" + partition + "-" + offset);
			}
		});
	}

	@GetMapping("/kafka/batch")
	public void sendMessage4() {
		for (int i = 0; i < 100; i++) {
			kafkaTemplate.send("topic3", i + "");
		}
	}

	@GetMapping("/kafka/transaction")
	public void sendMessage7() {
		try {
			// 报错则不发送消息
			kafkaTemplate.executeInTransaction(kafkaOperations -> {
				kafkaOperations.send("topic1", "test executeInTransaction");
				throw new RuntimeException("fail");
			});

			// 报错消息会发出去
			kafkaTemplate.send("topic1", "test executeInTransaction");
			throw new RuntimeException("fail");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
