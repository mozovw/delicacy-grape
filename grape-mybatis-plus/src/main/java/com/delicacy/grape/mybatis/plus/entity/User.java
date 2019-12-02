package com.delicacy.grape.mybatis.plus.entity;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@TableName("t_user")
public class User {

    private int id;

    private String username;

    private String password;

    private String account;

    private Boolean enabled;

    private int age;

    private String email;

}
