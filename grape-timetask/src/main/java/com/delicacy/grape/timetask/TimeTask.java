package com.delicacy.grape.timetask;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;

/**
 * 定时任务
 *
 * @author yutao
 * @create 2018-06-27 15:42
 **/
@Configuration
@EnableScheduling
public class TimeTask {

    @Scheduled(cron = "0 0 10 * * ?")
    public void task1() {
        System.out.println(Thread.currentThread().getName()+new Date());
    }

    @Scheduled(cron = "0 0/10 10-11 * * ?")
    public void task2()
    {
        System.out.println(Thread.currentThread().getName()+new Date());
    }

}
