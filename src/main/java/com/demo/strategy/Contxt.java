package com.demo.strategy;

/**
 * Created by Administrator on 2017/3/29.
 */
public class Contxt {
    private Strategy strategy;

    public Contxt(Strategy strategy) {
        this.strategy = strategy;
    }

    public void contextInterface(){
        strategy.trategyInterface();
    }
}
