package com.delicacy.grape.autoconfigure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

/**
 * @author yutao
 * @create 2019-10-27 9:44
 **/
@Import({UserProperties.class})
public class UserConfig {

    @Bean
    public User user(UserProperties userProperties){
        User user = userProperties.getUser();
        user.setUsername(userProperties.getUsername());
        return user;
    }

}
