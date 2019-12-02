package com.delicacy.grape.rocketmq.producer;


import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrapeRocketmqProducerApplication {

	public static void main(String[] args) throws MQClientException, InterruptedException {
		SpringApplication.run(GrapeRocketmqProducerApplication.class, args);
	}

}
