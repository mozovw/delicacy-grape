package com.delicacy.grape.rabbitmq.producer.rabbit.object;

import com.delicacy.grape.rabbitmq.producer.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ObjectSender {

	@Autowired
	private AmqpTemplate rabbitTemplate;
	@Autowired
	private ObjectMapper objectMapper;

	public void send(User user) throws JsonProcessingException {
		log.info("Sender object: " + user.toString());
		this.rabbitTemplate.convertAndSend("object", objectMapper.writeValueAsString(user));
//		this.rabbitTemplate.convertAndSend("object", user);
	}

}