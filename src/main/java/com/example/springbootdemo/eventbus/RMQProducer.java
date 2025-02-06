//package com.example.springbootdemo.eventbus;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.client.producer.SendCallback;
//import org.apache.rocketmq.client.producer.SendResult;
//import org.apache.rocketmq.spring.core.RocketMQTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.support.MessageBuilder;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class RMQProducer {
//    @Autowired
//    private RocketMQTemplate rocketMQTemplate;
//    private final String topic = "demo-topic";
//
//    // 1.同步发送消息
//    public void sendSyncMessage(String message){
//        rocketMQTemplate.syncSend(topic, MessageBuilder.withPayload(message).build());
//        log.info("同步发送结果: {}", message);
//    }
//
//    // 2.异步发送消息
//    public void sendAsyncMessage(String message){
//        rocketMQTemplate.asyncSend(topic, MessageBuilder.withPayload(message).build(), new SendCallback() {
//            @Override
//            public void onSuccess(SendResult sendResult) {
//                log.info("异步发送成功: {}", sendResult);
//            }
//            @Override
//            public void onException(Throwable throwable) {
//                log.error("异步发送失败", throwable);
//            }
//        });
//    }
//
//    // 3.单向发送消息
//    public void sendOneWayMessage(String message){
//        rocketMQTemplate.sendOneWay(topic, MessageBuilder.withPayload(message).build());
//        log.info("单向消息发送成功");
//    }
//}