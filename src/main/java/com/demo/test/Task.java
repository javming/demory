package com.demo.test;

import java.util.concurrent.TimeUnit;

/**
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-11-02-14:47
 */
public class Task implements Runnable{

    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
