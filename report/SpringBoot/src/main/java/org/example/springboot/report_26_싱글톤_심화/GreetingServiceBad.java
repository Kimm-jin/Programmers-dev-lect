package org.example.springboot.report_26_싱글톤_심화;

public class GreetingServiceBad {
    private String name;
    private static final GreetingServiceBad instance = new GreetingServiceBad();
    private GreetingServiceBad(){}
    static GreetingServiceBad getInstance(){return instance;}

    String greet(String reqName){
        this.name=reqName;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.name;
    }

}
