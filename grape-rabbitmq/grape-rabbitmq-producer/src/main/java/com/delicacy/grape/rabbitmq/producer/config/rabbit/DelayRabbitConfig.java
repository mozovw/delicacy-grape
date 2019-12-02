package com.delicacy.grape.rabbitmq.producer.config.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yutao
 * @create 2019-11-05 15:14
 **/
@Configuration
public class DelayRabbitConfig {

    final static String queueName = "queue.info";
    final static String exchangeName = "direct.info";
    final static String key = "info";

    final static String ttlqueueName = "queue.info.ttl";
    final static String ttlexchangeName = "direct.info.ttl";
    final static String ttlkey = "info.ttl";

    @Bean
    public Queue infoQueue() {
        return new Queue(queueName);
    }

    @Bean
    public DirectExchange infoExchange() {
        return  (DirectExchange) ExchangeBuilder
                .directExchange(exchangeName)
                .durable(true)
                .build();
    }

    /**
     * 延迟队列绑定自定义交换器l
     *
     * @param infoQueue    队列
     * @param infoExchange 延迟交换器
     */
    @Bean
    public Binding infoBinding(Queue infoQueue, DirectExchange infoExchange) {
        return BindingBuilder
                .bind(infoQueue)
                .to(infoExchange)
                .with(key);
    }

    /**
     * 延迟队列
     */
    @Bean
    public Queue ttlQueue() {
        return QueueBuilder
                .durable(ttlqueueName)
                .withArgument("x-dead-letter-exchange", exchangeName)//到期后转发的交换机
                .withArgument("x-dead-letter-routing-key", key)//到期后转发的路由键
                .build();
    }

    @Bean
    public DirectExchange ttlExchange() {
        return (DirectExchange) ExchangeBuilder
                .directExchange(ttlexchangeName)
                .durable(true)
                .build();
    }

    @Bean
    public Binding ttlBinding(DirectExchange ttlExchange,Queue ttlQueue){
        return BindingBuilder
                .bind(ttlQueue)
                .to(ttlExchange)
                .with(ttlkey);
    }
}
