package com.delicacy.grape.rabbitmq.producer.config.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @author yutao
 * @create 2019-11-05 13:19
 **/

@Configuration
public class OrderRabbitConfig {
    final static String queueName = "queue.order";
    final static String exchangeName = "direct.order";
    final static String key = "add";

    @Bean
    public Queue queue() {
        return new Queue(queueName, true, false, false, new HashMap<>());
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName, true, false, new HashMap<>());
    }

    @Bean
    public Binding bindingExchangeOrder(Queue queue,DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(key);
    }
}
