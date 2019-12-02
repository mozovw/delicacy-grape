package com.delicacy.grape.rabbitmq.producer.rabbit.order;

import com.delicacy.grape.rabbitmq.producer.model.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@Slf4j
@RestController
public class OrderSender {

    @Autowired
    private ObjectMapper objectMapper;

    //自动注入RabbitTemplate模板类
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @PutMapping("sendOrder")
    //发送消息方法调用: 构建自定义对象消息
    public void sendOrder(@RequestBody Order order) throws Exception {
        //消息唯一ID
        CorrelationData correlationData = new CorrelationData(order.getMessageId());
        rabbitTemplate.convertAndSend("direct.order", "add", objectMapper.writeValueAsString(order), correlationData);
    }
    @PutMapping("sendOrder2")
    //发送消息方法调用: 构建自定义对象消息
    public void sendOrder2(@RequestBody Order order) throws Exception {
        rabbitAdmin.declareExchange(new DirectExchange("direct.order", true, false, new HashMap<>()));
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        messageProperties.setContentType("UTF-8");
        Message message = new Message(objectMapper.writeValueAsString(order).getBytes(), messageProperties);
        rabbitTemplate.send("direct.order", "add", message, new CorrelationData(order.getMessageId()));

    }
}