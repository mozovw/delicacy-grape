package com.delicacy.grape.rabbitmq.producer.config.rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DefaultRabbitConfig {
    final static String hello = "hello";
    final static String neo = "many";
    final static String object = "object";

    @Bean
    public Queue helloQueue() {
        return new Queue(hello);
    }

    @Bean
    public Queue neoQueue() {
        return new Queue(neo);
    }

    @Bean
    public Queue objectQueue() {
        return new Queue(object);
    }


}
