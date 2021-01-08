package com.exercise.mq;

import com.exercise.model.Phone;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 王能顺
 * @Date: 2021-1-8 17:22:26
 * <p>
 * 消息消费者
 */
@RestController
public class Consumer {

    // 监听和读取queue消息
    @JmsListener(destination = "amq.queue")
    public void readActiveQueue(Phone phone) {
        System.out.println("接收到queue消息：" + phone);
    }

    // 监听和读取topic消息
    @JmsListener(destination = "amq.topic")
    public void readActiveTopic(Phone phone) {
        System.out.println("接收到topic消息：" + phone);
    }

}