package com.delicacy.grape.bean;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GrapeBeanApplication {

	public static void main(String[] args) {
		SpringApplication.run(GrapeBeanApplication.class, args);
	}

	@Bean
	public Book book(){
		Book book = new Book();
		book.setBookName("think in Java");
		return book;
	}

	@Bean
	public MyBeanPostProcessor myBeanPostProcessor(){
		return new MyBeanPostProcessor();
	}


}

