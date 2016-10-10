package com.demo.bean.lessions;

import java.util.ArrayList;

/**
 * 自然科学
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-10-11:05
 */
public class ScienceLession extends LessionModel{
    private ArrayList<LessionModel> lessions = new ArrayList<LessionModel>();

    public ScienceLession() {
        this.name="Sciencelession";
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
