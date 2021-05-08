package com.delicacy.grape.mybatis.plus.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.delicacy.grape.mybatis.plus.entity.User;
import com.delicacy.grape.mybatis.plus.mapper.UserMapper;
import com.delicacy.grape.mybatis.plus.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author yutao
 * @create 2019-10-22 10:43
 **/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{
    @Override
    public User get(String name) {
        UserMapper baseMapper = this.getBaseMapper();
        User user = baseMapper.selectOne(Wrappers.<User>query().lambda()
            .eq(User::getUsername,"乔峰"));
        return user;
}
}
