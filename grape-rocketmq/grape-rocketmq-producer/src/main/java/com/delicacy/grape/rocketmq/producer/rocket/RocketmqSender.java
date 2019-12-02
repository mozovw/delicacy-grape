package com.delicacy.grape.rocketmq.producer.rocket;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author yutao
 * @create 2019-11-08 14:08
 **/
@Slf4j
@AllArgsConstructor
@Service
public class RocketmqSender {
  static final   String ipAddr = "192.168.189.142";

    @PostConstruct
    public void send() throws MQClientException, InterruptedException {
        DefaultMQProducer producer = new DefaultMQProducer("test-group");
        producer.setNamesrvAddr(ipAddr + ":9876");
        producer.start();
        for (int i = 0; i < 20; i++) {
            try {
                byte[] bytes = ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET);
                Message msg = new Message("test-topic" /* Topic */,
                        "TagA" /* Tag */,
                        bytes /* Message body */
                );
                SendResult sendResult = producer.send(msg);
                if (!sendResult.getSendStatus().equals(SendStatus.SEND_OK)){
                    log.info("发送失败，status：{}",sendResult);
                    return;
                }
                log.info("发送成功，status：{}",sendResult);
            } catch (Exception e) {
                e.printStackTrace();
                Thread.sleep(1000);
            }
        }
        producer.shutdown();
    }
}
