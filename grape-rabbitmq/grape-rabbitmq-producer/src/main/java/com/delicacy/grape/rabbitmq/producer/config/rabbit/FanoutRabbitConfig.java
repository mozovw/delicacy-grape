package com.delicacy.grape.rabbitmq.producer.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class FanoutRabbitConfig {

    @Bean
    public Queue AQueue() {
        return new Queue("fanout.A");
    }

    @Bean
    public Queue BQueue() {
        return new Queue("fanout.B");
    }

    @Bean
    public Queue CQueue() {
        return new Queue("fanout.C");
    }
    
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    Binding bindingExchangeA(Queue AQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(AQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeB(Queue BQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(BQueue).to(fanoutExchange);
    }

    @Bean
    Binding bindingExchangeC(Queue CQueue, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(CQueue).to(fanoutExchange);
    }


}
