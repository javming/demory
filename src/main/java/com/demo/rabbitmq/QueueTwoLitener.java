package com.demo.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-27-17:11
 */
public class QueueTwoLitener implements MessageListener{

    public void onMessage(Message message) {
        try {
            System.out.println("receive--two-->"+new String(message.getBody(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
    }
}
