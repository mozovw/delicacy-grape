package com.delicacy.grape.quartz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class GrapeQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrapeQuartzApplication.class, args);
	}

}
