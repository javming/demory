package com.demo.bean.lessions;

/**
 * 无机化学
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-10-11:36
 */
public class InoganicChemical extends LessionModel {
    public InoganicChemical() {
        this.name="InoganicChemical";
    }

    @Override
    public void print() {
        System.out.println(getName());
    }
}
