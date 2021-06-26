package com.delicacay.grape.mybatis.controller;

import com.delicacay.grape.mybatis.entity.Description;
import com.delicacay.grape.mybatis.entity.User;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.instrument.TransformerManager;

@RestController
public class MybatisController {

    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    @RequestMapping("/user/{id}")
    public User user(@PathVariable int id) {
        User user = sqlSessionTemplate.selectOne("com.delicacay.grape.mybatis.annotation.UserMapper.selectUser", id);
        return user;
    }

    @Autowired
    private PlatformTransactionManager transactionManager;


    @Autowired
    private TransactionTemplate transactionTemplate;

    @RequestMapping("/add/user")
    public void addUser() {
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        try {
            addOneUser();
            if (1==1){
                throw new RuntimeException();
            }
            // 事务提交
            transactionManager.commit(status);
        } catch (Exception e) {
            // 事务提交
            transactionManager.rollback(status);
            throw e;
        }
    }

    @RequestMapping("/add/user2")
    public void addUser2() {
        transactionTemplate.execute(transactionStatus -> {
            addOneUser();
//            if (1==1){
//                throw new RuntimeException();
//            }
            return true;
        });
    }

    private void addOneUser() {
        // 事务操作
        User parameter = new User();
        parameter.setAge(11);
        parameter.setId(149);
        parameter.setName("lucy");
        Description description = new Description();
        description.setCity("jiangsu");
        description.setProvince("xuzhou");
        parameter.setDescription(description);
        parameter.setHeight(33f);
        sqlSessionTemplate.insert("com.delicacay.grape.mybatis.annotation.UserMapper.addUser", parameter);
    }

}
