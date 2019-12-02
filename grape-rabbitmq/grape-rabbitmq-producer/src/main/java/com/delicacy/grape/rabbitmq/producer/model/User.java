package com.delicacy.grape.rabbitmq.producer.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by summer on 2016/11/29.
 */
@Data
public class User implements Serializable{
    private static final long serialVersionUID = -8223831448904043217L;

    private String name;

    private String pass;

}
