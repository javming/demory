package com.demo.bean.lessions;

/**
 * 生物
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-10-11:32
 */
public class Biological extends LessionModel {
    public Biological() {
        this.name="Biological";
    }

    @Override
    public void print() {
        System.out.println(getName());
    }
}
