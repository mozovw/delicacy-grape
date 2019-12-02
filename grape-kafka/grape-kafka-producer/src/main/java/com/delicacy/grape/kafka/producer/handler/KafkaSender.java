package com.delicacy.grape.kafka.producer.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

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
@Slf4j
@Component
public class KafkaSender {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /**
     * 发送消息到kafka,主题为test
     */
    public void send(Object obj) throws JsonProcessingException {
        log.info("准备发送消息为：{}", objectMapper.writeValueAsString(obj));
        //发送消息
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send("test-group", obj);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理
                log.info("生产者 发送消息失败：" + throwable.getMessage());
            }
            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //成功的处理
                log.info("生产者 发送消息成功：" + stringObjectSendResult.toString());
            }
        });

    }
}

