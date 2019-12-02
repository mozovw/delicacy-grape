package com.delicacy.grape.rabbitmq.consumer.rabbit.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.tools.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeoutException;

//配置监听的哪一个队列，同时在没有queue和exchange的情况下会去创建并建立绑定关系
@RabbitListener(bindings = @QueueBinding(
        value = @Queue(value = "queue.order", durable = "true"),
        exchange = @Exchange(name = "direct.order"),
        key = "add"
)
)
@Slf4j
@Component
public class OrderReceiver {

    @Autowired
    ObjectMapper objectMapper;

    @RabbitHandler//如果有消息过来，在消费的时候调用这个方法
    public void onOrderMessage(String order, Message message, Channel channel)  {

        //消费者操作
        log.info("---------收到消息，开始消费---------");
        log.info("订单信息：" + order);

        /**
         * Delivery Tag 用来标识信道中投递的消息。RabbitMQ 推送消息给 Consumer 时，会附带一个 Delivery Tag，
         * 以便 Consumer 可以在消息确认时告诉 RabbitMQ 到底是哪条消息被确认了。
         * RabbitMQ 保证在每个信道中，每条消息的 Delivery Tag 从 1 开始递增。
         */
        Long deliveryTag = message.getMessageProperties().getDeliveryTag();
        /**
         *  multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认
         *  如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认
         */
        boolean multiple = false;
        try {
            // 通知 MQ 消息已被成功消费,可以ACK了
            channel.basicAck(deliveryTag, multiple);
            // next step
        } catch (IOException e) {
            try {
                // 处理失败,重新压入MQ
                channel.basicNack(deliveryTag, false, true);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


    }
}