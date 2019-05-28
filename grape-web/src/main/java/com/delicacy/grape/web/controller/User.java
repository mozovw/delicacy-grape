package com.delicacy.grape.web.controller;


import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

@Data
public class User extends ResourceSupport {

    private String name;

    private int age;

}
