package com.delicacy.grape.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication(scanBasePackages = {
        "com.delicacy.grape.oauth.config.oauth",
        "com.delicacy.grape.oauth.rest"})
@EnableCaching
public class GrapeOath2Application {

    public static void main(String[] args) {
        SpringApplication.run(GrapeOath2Application.class, args);
    }

}
