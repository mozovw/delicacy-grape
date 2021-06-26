package com.delicacay.grape.mybatis.entity;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

@Data
public class User {

    private int id;

    private String name;

    private int age;

    private Description description;

    private float height;

    public String getDesc() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(description);
    }
}
