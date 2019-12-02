package com.delicacy.grape.rabbitmq.consumer.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yutao
 * @create 2019-11-05 3:17
 **/
@Data
public class Order implements Serializable{
    private static final long serialVersionUID = 2339364136227268475L;
    private Long id;
    private String messageId;
    private String name;

}
