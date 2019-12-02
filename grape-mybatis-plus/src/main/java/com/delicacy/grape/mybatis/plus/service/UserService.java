package com.delicacy.grape.mybatis.plus.service;


import com.delicacy.grape.mybatis.plus.entity.User;

/**
 * @author yutao
 * @create 2019-10-22 10:41
 **/
public interface UserService {
    User get(String name);
}
