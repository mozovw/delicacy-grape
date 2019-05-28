package com.delicacy.grape.web.servletinitializer;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan(basePackages = {"com.delicacy.grape.web.servlet"})
public class MySpringBootServletInitializer{

}
