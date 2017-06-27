package com.demo.util;

import net.rubyeye.xmemcached.MemcachedClient;
import net.rubyeye.xmemcached.XMemcachedClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by Administrator on 2017/2/16.
 */
public class MemcacheUtil {
    private static final Logger log = LoggerFactory.getLogger(MemcacheUtil.class);
    private static final String IP="211.157.179.221";
    private static final int PORT=11211;


    public static MemcachedClient getMemcacheClient(String server,int port) {
        MemcachedClient client = null;
        try {
            client = new XMemcachedClient(server,port);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return client;
    }

    public static String get(String key){
        MemcachedClient client = getMemcacheClient(IP, PORT);
        String value = null;
        try {
            value = client.get(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.shutdown();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    public static void set(String key,int expire,String value){
        MemcachedClient client = getMemcacheClient(IP, PORT);
        try {
            client.set(key,expire,value);
            log.info("存储成功！");
        } catch (Exception e) {
            log.error("存储失败！");
            e.printStackTrace();

        }
    }

    public static void set(String key,String value){
        set(key,0,value);
    }

}
