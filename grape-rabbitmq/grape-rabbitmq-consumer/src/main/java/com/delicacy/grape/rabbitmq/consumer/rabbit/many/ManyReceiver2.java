package com.delicacy.grape.rabbitmq.consumer.rabbit.many;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
@Slf4j
@Component
@RabbitListener(queues = "many")
public class ManyReceiver2 {

    @RabbitHandler
    public void process(String neo) {
        log.info("Receiver 2: " + neo);
    }

}
