package com.delicacy.grape.roctketmq.consumer;


import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrapeRoctketmqConsumerApplication {

    public static void main(String[] args) throws MQClientException, InterruptedException {
        SpringApplication.run(GrapeRoctketmqConsumerApplication.class, args);

    }

}
