package com.delicacy.grape.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GrapeCacheApplication {

    @Autowired
    private CacheService cacheService;

    public static void main(String[] args) {
        SpringApplication.run(GrapeCacheApplication.class, args);
    }

}
