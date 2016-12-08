package com.demo.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

import java.io.UnsupportedEncodingException;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-13-15:01
 */
public class QueueOneLitener implements MessageListener{

    public void onMessage(Message message) {
        try {
            System.out.println("receive--one-->"+new String(message.getBody(),"utf-8"));
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        }
    }
}
