package com.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class ChouJiang {
    private static final List<String> list = new ArrayList<>();
    private static Random random = new Random();
    private static int count = 5;
    static {
        list.add("macBookPro_8988");
        list.add("三星NOTE7_5695");
        list.add("小米4_2688");
        list.add("联想P612_866");
        list.add("iphone7_5688");
    }

    public static synchronized String[] getJ(){
        if (count<=0){
            return null;
        }
        int i = random.nextInt(count);
        String a = list.get(i);
        list.remove(i);
        count--;
        return a.split("_");
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (count<=0) return;
                    String[] j = getJ();
                    if (j!= null){
                        System.out.println("t1奖品："+j[0]+",price:"+j[1]);
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (count<=0) return;
                    String[] j = getJ();
                    if (j!= null){
                        System.out.println("t2奖品："+j[0]+",price:"+j[1]);
                    }
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });
        t1.start();
        t2.start();
    }
}
