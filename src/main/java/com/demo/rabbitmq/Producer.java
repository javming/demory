package com.demo.rabbitmq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 消息发送者
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-13-13:11
 */
@Repository
public class Producer {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendDataToCrQueue(Object obj) {
        System.out.println("amqpTemplate:  "+amqpTemplate);
        amqpTemplate.convertAndSend("one.two", obj);
        System.out.println("message sent success");
    }
}
