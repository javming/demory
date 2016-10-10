package com.demo.bean.lessions;

/**
 * 有机化学
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-10-11:34
 */
public class OrganicChemical extends LessionModel{
    public OrganicChemical() {
        this.name="OrganicChemical";
    }

    @Override
    public void print() {
        System.out.println(getName());
    }
}
