package com.delicacy.grape.web.servlet;


import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ServletComponentScan(basePackages = {"com.delicacy.grape.web.servlet"})
public class AnnotationSpringBootServletInitializer {

}
