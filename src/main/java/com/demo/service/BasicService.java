package com.demo.service;

import com.demo.rabbitmq.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * service基类
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-11-29-10:05
 */
@Service
public class BasicService {
    protected Logger log = LoggerFactory.getLogger(BasicService.class);

    @Autowired
    private Producer producer;

    public Logger getLog() {
        return log;
    }

    public void setLog(Logger log) {
        this.log = log;
    }
}
