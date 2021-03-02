package com.intecsec.java.mq.controller;

import com.intecsec.java.util.JsonUtils;
import com.intecsec.java.vo.Message;
import org.springframework.kafka.core.KafkaTemplate;
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
		kafkaTemplate.send("test", JsonUtils.toJson(message));
	}

}
