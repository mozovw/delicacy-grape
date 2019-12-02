package com.delicacy.grape.shardingsphere;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
@MapperScan("com.delicacy.grape.shardingsphere.mapper")
public class GrapeShardingsphereApplication {

    public static void main(String[] args) {
        SpringApplication.run(GrapeShardingsphereApplication.class, args);
    }


}
