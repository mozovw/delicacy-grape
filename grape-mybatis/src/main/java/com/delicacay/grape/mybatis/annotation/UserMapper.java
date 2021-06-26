package com.delicacay.grape.mybatis.annotation;


import com.delicacay.grape.mybatis.entity.User;
import com.delicacay.grape.mybatis.handler.DescriptionTypeHandler;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Results(value = {
            @Result(property = "id", column = "id", id = true),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age"),
            @Result(property = "description", column = "desc", typeHandler = DescriptionTypeHandler.class),
            @Result(property = "height", column = "height")
    })
    @Select("SELECT id,name,age,`desc`,height FROM user WHERE id = #{id}")
    User selectUser(int id);

    User selectOneUser(int id);

    @Insert("INSERT INTO `test`.`user`(`id`, `name`, `age`, `desc`, `height`) " +
            "VALUES (#{id}, #{name}, #{id}, #{desc}, #{height})")
    void addUser(User user);
}
