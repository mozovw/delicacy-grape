package com.delicacy.grape.rabbitmq.producer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.api.RabbitListenerErrorHandler;
import org.springframework.amqp.rabbit.listener.exception.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;

/**
 * @author yutao
 * @create 2019-11-02 17:14
 **/
@Slf4j
@Configuration
public class ExtraConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Bean
    @Autowired
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        
        // 设置RabbitTemplate的Mandatory属性为true
        rabbitTemplate.setMandatory(true);
        // 为RabbitTemplate设置ReturnCallback
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            try {
                log.info("ReturnCallback.......");
                log.info("replyCode:" + replyCode);
                log.info("replyText:" + replyText);
                log.info("exchange:" + exchange);
                log.info("routingKey:" + routingKey);
                log.info("properties:" + message.getMessageProperties());
                log.info("body:" + new String(message.getBody(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        });
        // 为RabbitTemplate设置ConfirmCallback
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            log.info("ConfirmCallback.......");
            if (ack) {
                //如果confirm返回成功 则进行更新
                log.info("发送成功...");
            } else {
                //失败则进行具体的后续操作:重试 或者补偿等手段
                log.error("异常处理...");
            }
            log.info(correlationData.getId());
        });
        
        return rabbitTemplate;
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }
    
    

    @Bean
    public RabbitListenerErrorHandler rabbitListenerErrorHandler(){
        return (amqpMessage, message, exception) -> {
            log.error(exception.getMessage());
            throw exception;
        };
    }

}
