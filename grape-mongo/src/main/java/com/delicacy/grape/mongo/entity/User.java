package com.delicacy.grape.mongo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by summer on 2017/5/5.
 */
@Data
public class User implements Serializable {
        private static final long serialVersionUID = -3258839839160856613L;
        private Long id;
        private String userName;
        private String passWord;

}