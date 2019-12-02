package com.delicacy.grape.rabbitmq.consumer.rabbit.object;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RabbitListener(queues = "object", errorHandler = "rabbitListenerErrorHandler")
public class ObjectReceiver {

    @RabbitHandler
    public void process(String user, Message message, Channel channel) {
        log.info("Receiver object : {}", user);
    }

}
