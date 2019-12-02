package com.delicacy.grape.elasticsearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@SpringBootApplication
public class GrapeElasticsearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrapeElasticsearchApplication.class, args);
	}

}
