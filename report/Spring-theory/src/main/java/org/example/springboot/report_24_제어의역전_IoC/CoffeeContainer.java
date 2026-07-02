package org.example.springboot.report_24_제어의역전_IoC;

public class CoffeeContainer {
    CoffeeMaker getCoffeeMaker(String type){
        Bean bean;
        if(type.equals("ColombiaBean")){
            bean = new ColombiaBean();
        }else if(type.equals("EthiopiaBean")){
            bean = new EthiopiaBean();
        }else throw new IllegalArgumentException("지원하지 않는 원두 : "+type);
        MilkFrother milkFrother = new MilkFrother();
        return new CoffeeMaker(bean, milkFrother);
    }
}
