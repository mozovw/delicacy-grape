package com.delicacy.grape.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author yutao
 * @create 2019-11-09 17:09
 **/
@Slf4j
@Component
public class QuartzTask {

    public void execute(){
       log.info("run..........");
    }
}
