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
@Order(1)
public class Order1 {

    @PostConstruct
    public void init() {
        log.info("Order1 init");
    }


}
