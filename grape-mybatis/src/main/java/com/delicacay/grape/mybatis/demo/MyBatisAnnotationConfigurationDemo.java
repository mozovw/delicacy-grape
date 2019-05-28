package com.delicacay.grape.mybatis.demo;


import com.delicacay.grape.mybatis.annotation.UserMapper;
import com.delicacay.grape.mybatis.entity.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Properties;

public class MyBatisAnnotationConfigurationDemo {

    public static void main(String[] args) throws Exception {

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        InputStream inputStream = classLoader.getResourceAsStream("mybatis/mybatis-constant.xml");

        Reader reader = new InputStreamReader(inputStream, "UTF-8");

        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

        SqlSessionFactory sqlSessionFactory = builder.build(reader, "dev", new Properties());

        SqlSession sqlSession = sqlSessionFactory.openSession();

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        User user = userMapper.selectUser(145);

        System.out.println(user);

        sqlSession.close();

    }

}
