package com.exercise.controller;

import com.exercise.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;
import javax.jms.Topic;

/**
 * @Author: 王能顺
 * @Date: 2021年1月8日17:17:36
 * <p>
 * 消息发送者
 */
@RestController
public class MQProducerController {

    // JMS 消息发送模版
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    @Autowired
    private Topic topic;

    // 发送Queue消息
    @GetMapping("/sendQueueMsg")
    public void sendQueueMsg() {
        this.jmsMessagingTemplate.convertAndSend(this.queue, new Phone("sendQueueMsg", "queue发送者"));
    }

    // 发送Topic消息
    @GetMapping("/sendTopicMsg")
    public void sendTopicMsg() {
        this.jmsMessagingTemplate.convertAndSend(this.topic, new Phone("sendTopicMsg", "topic发送者"));
    }

}