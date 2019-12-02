package com.delicacy.grape.rabbitmq.producer.rabbit.delay;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@Component
public class DelaySender {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send() {
		rabbitTemplate.convertAndSend("direct.info.ttl", "info.ttl", "delay message, delay 5s, " +  new Date(), message -> {
			message.getMessageProperties().setExpiration(String.valueOf(5000));
			return message;
		});
	}

}