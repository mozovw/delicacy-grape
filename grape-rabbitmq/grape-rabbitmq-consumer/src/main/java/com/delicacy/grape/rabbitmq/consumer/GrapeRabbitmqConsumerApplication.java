package com.delicacy.grape.rabbitmq.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
public class GrapeRabbitmqConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrapeRabbitmqConsumerApplication.class, args);
	}

}
