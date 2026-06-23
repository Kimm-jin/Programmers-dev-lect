package org.example.springboot.report_26_싱글톤_심화;

public class GreetingServiceGood {
    private static final GreetingServiceGood instance = new GreetingServiceGood();
    private GreetingServiceGood(){};
    static GreetingServiceGood getInstance(){return instance;}

    String greet(String reqName){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return reqName;
    }

}
