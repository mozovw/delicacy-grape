package com.delicacy.grape.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@EnableConfigurationProperties
public class GrapeWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrapeWebApplication.class, args);
	}
}
