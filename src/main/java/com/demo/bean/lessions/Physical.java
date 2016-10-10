package com.demo.bean.lessions;

/**
 * 物理
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-10-11:28
 */
public class Physical extends LessionModel{
    public Physical() {
        this.name="Physical";
    }

    @Override
    public void print() {
        System.out.println(getName());
    }
}
