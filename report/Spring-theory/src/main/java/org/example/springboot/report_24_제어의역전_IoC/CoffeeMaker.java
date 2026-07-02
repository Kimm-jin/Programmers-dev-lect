package org.example.springboot.report_24_제어의역전_IoC;

public class CoffeeMaker {

    private Bean bean;
    private MilkFrother milkFrother;
    CoffeeMaker(Bean bean, MilkFrother milkFrother){
        this.bean=bean; this.milkFrother=milkFrother;
    }
    void print(){
        System.out.println(bean.name()+" 커피" + milkFrother.froth());
    }
}

class MilkFrother {
    String froth() {
        return "라떼";
    }
}