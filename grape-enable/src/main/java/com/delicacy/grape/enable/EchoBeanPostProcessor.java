package com.delicacy.grape.enable;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.AntPathMatcher;

import java.util.List;

@Slf4j
@Getter
@Setter
//实现BeanPostProcessor接口的类，放入spring容器中后，容器启动和关闭时会执行以下两个重写的方法
public class EchoBeanPostProcessor implements BeanPostProcessor {

    //getter、setter省略，读者在试验的时候要加上
    private List<String> packages;
    AntPathMatcher antPathMatcher = new AntPathMatcher();

    //该方法在spring容器初始化前执行
    @Override
    public Object postProcessBeforeInitialization(Object bean, String s) throws BeansException {
        for (String pack : packages) {
            if ( antPathMatcher.match(pack,bean.getClass().getName())) {
                log.warn("echo bean: {}", bean.getClass().getName());
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {
        return bean;
    }
}