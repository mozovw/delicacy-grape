package com.delicacy.grape.rabbitmq.producer.rabbit.many;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ManySender2 {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	public void send(int i) {
		String context = "spirng boot many queue"+" ****** "+i;
		log.info("Sender2 : " + context);
		this.rabbitTemplate.convertAndSend("many", context);
	}

}