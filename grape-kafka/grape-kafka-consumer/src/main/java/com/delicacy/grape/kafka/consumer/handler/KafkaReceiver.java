package com.delicacy.grape.kafka.consumer.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 消息处理器
 * </p>
 *
 * @package: com.xkcoding.mq.kafka.handler
 * @description: 消息处理器
 * @author: yangkai.shen
 * @date: Created in 2019-01-07 14:58
 * @copyright: Copyright (c) 2019
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Component
@Slf4j
public class KafkaReceiver {


    int count = 0 ;
    int count2 = 0 ;

    // 同一组下消费者消费消息是一样了的，而且是独立的

    @KafkaListener(id= "Receiver1",topics = "test-group")
    public void handleMessage(ConsumerRecord record, Acknowledgment acknowledgment){
        String message = null;
        try {
            message = (String) record.value();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (++count > 5) return;
            log.info("线程：{},收到消息: {}",Thread.currentThread().getName(), message);
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

    @KafkaListener(id= "Receiver2",topics = "test-group")
    public void handleMessage2(ConsumerRecord record, Acknowledgment acknowledgment){
        String message = null;
        try {
            message = (String) record.value();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            if (++count2 <= 5) return;
            log.info("线程：{},收到消息: {}",Thread.currentThread().getName(), message);
            // 手动提交 offset
            acknowledgment.acknowledge();
        }
    }

//    @KafkaListener(id= "Receiver2",topics = "test-group")
//    public void handleMessage2(ConsumerRecord record, Acknowledgment acknowledgment){
//        try {
//            if (++count2 <= 5) return;
//            String message = (String) record.value();
//            log.info("线程：{},收到消息: {}",Thread.currentThread().getName(), message);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        } finally {
//            // 手动提交 offset
//            acknowledgment.acknowledge();
//        }
//    }



//    @KafkaListener(topics = "test-group")
//    public void handleMessage(ConsumerRecord record) {
//        try {
//            String message = (String) record.value();
//            log.info("收到消息: {}", message);
//        } catch (Exception e) {
//            log.error(e.getMessage(), e);
//        }
//    }
}
