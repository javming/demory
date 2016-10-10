package com.demo.bean.lessions;

/**
 * 课程父类__(测试java设计模式--组合模式)
 *
 * @AUTHOR zhaoming@eduspace
 * @CREATE 2016-10-10-10:35
 */
public abstract class  LessionModel {
    protected String name;

    public String getName() {
        return name;
    }
    public void print(){
        throw new UnsupportedOperationException();
    }
    public void add(LessionModel lessionModel){
        throw new UnsupportedOperationException();
    }
    public void remove(LessionModel lessionModel){
        throw new UnsupportedOperationException();
    }
    public LessionModel getChild(){
        throw  new UnsupportedOperationException();
    }

}
class LTest{
    public static void main(String[] args){
        LessionModel scienceLession= new ScienceLession();
        LessionModel physical = new Physical();
        LessionModel biological = new Biological();
        LessionModel chemical = new Chemical();
        LessionModel organicChemical = new OrganicChemical();
        LessionModel inoganicChemical = new InoganicChemical();

        scienceLession.add(physical);
        scienceLession.add(biological);
        scienceLession.add(chemical);

        chemical.add(organicChemical);
        chemical.add(inoganicChemical);

        scienceLession.print();
    }
}
