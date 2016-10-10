package com.demo.bean.lessions;

import java.util.ArrayList;

/**
 * 化学
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-10-11:30
 */
public class Chemical extends LessionModel{
    private ArrayList<LessionModel> lessions = new ArrayList<LessionModel>();

    public Chemical() {
        this.name="Chemical";
    }

    @Override
    public void print() {
        System.out.println(getName());
        for (LessionModel model:lessions){
            System.out.print("\t");
            model.print();
        }
    }

    @Override
    public void add(LessionModel lessionModel) {
        lessions.add(lessionModel);
    }

    @Override
    public void remove(LessionModel lessionModel) {
        lessions.remove(lessionModel);
    }
}
