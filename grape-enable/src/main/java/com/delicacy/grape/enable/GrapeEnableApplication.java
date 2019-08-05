package com.delicacy.grape.enable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableEcho(packages = {"com.delicacy.grape.enable.entity.*"})
@SpringBootApplication
public class GrapeEnableApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrapeEnableApplication.class, args);
	}

}
