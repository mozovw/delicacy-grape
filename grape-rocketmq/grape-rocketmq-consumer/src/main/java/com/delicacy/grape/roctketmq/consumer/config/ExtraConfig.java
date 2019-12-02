package com.delicacy.grape.roctketmq.consumer.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yutao
 * @create 2019-11-02 17:14
 **/
@Slf4j
@Configuration
public class ExtraConfig {

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

}
