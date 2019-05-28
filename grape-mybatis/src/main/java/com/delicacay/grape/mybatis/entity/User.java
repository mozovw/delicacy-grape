package com.delicacay.grape.mybatis.entity;


import lombok.Data;

@Data
public class User {

    private int id;

    private String name;

    private int age;

    private Description description;

    private float height;


}
