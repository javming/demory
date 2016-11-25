package com.demo.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-27-17:11
 */
public class QueueTwoLitener implements MessageListener{

    public void onMessage(Message message) {

    }
}
