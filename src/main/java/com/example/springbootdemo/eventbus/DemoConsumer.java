//package com.example.springbootdemo.eventbus;
//
//import lombok.extern.slf4j.Slf4j;
//import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
//import org.apache.rocketmq.spring.core.RocketMQListener;
//import org.springframework.stereotype.Component;
//
//@Slf4j
//@Component
//@RocketMQMessageListener(topic = "demo-topic",consumerGroup = "demo-consumer-group")
//public class DemoConsumer implements RocketMQListener<String> {
//    @Override
//    public void onMessage(String msg) {
//        log.info("收到消息: {}", msg);
//    }
//}
