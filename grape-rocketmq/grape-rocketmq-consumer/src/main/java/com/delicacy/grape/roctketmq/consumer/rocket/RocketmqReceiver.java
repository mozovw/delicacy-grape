package com.delicacy.grape.roctketmq.consumer.rocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.*;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author yutao
 * @create 2019-11-08 14:08
 **/
@Slf4j
@AllArgsConstructor
@Service
public class RocketmqReceiver {
    static final String ipAddr = "192.168.189.142";

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void receive() throws MQClientException, InterruptedException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("test-group");
        consumer.setNamesrvAddr(ipAddr + ":9876");
        consumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        consumer.subscribe("test-topic", "*");
        consumer.registerMessageListener(messageListenerOrderly());
        consumer.start();
        log.info("Consumer Started");

    }
    public MessageListenerConcurrently messageListenerConcurrently(){
        return new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                                            ConsumeConcurrentlyContext context) {
                try {
                    msgs.stream().forEach(e -> {
                        byte[] body = e.getBody();
                        try {
                            log.info(Thread.currentThread().getName() + " 接收的消息: {}", objectMapper.writeValueAsString(new String(body)));
                        } catch (JsonProcessingException e1) {
                            e1.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeConcurrentlyStatus.RECONSUME_LATER; //稍后再试
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        };
    }

    public MessageListenerOrderly messageListenerOrderly(){
        return  new MessageListenerOrderly() {
            @Override
            public ConsumeOrderlyStatus consumeMessage(List<MessageExt> msgs, ConsumeOrderlyContext consumeOrderlyContext) {
                try {
                    msgs.stream().forEach(e -> {
                        byte[] body = e.getBody();
                        try {
                            log.info(Thread.currentThread().getName() + " 接收的消息: {}", objectMapper.writeValueAsString(new String(body)));
                        } catch (JsonProcessingException e1) {
                            e1.printStackTrace();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    return ConsumeOrderlyStatus.SUSPEND_CURRENT_QUEUE_A_MOMENT; //稍后再试
                }
                return ConsumeOrderlyStatus.SUCCESS;
            }
        };
    }


}
