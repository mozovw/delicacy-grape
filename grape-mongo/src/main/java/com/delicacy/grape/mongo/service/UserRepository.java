package com.delicacy.grape.mongo.service;


import com.delicacy.grape.mongo.entity.User;

/**
 * Created by summer on 2017/5/5.
 */
 public interface UserRepository {

     void saveUser(User user);

     User findUserByUserName(String userName);

     long updateUser(User user);

     void deleteUserById(Long id);

}