package com.delicacy.grape.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author yutao
 * @create 2019-11-28 15:13
 **/
@Slf4j
@Component
@Order(2)
public class Order2 {

    @PostConstruct
    public void init() {
        log.info("Order2 init");
    }


}
