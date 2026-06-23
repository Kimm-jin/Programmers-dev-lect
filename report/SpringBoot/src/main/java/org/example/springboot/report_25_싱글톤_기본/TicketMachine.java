package org.example.springboot.report_25_싱글톤_기본;

public class TicketMachine {
    private int count =0;

    private static final TicketMachine instance = new TicketMachine();
    private TicketMachine(){};
    // 여러 스레드가 동시에 들어오면 객체가 두개 생길 수 있음, 동기화로 막기
    static synchronized TicketMachine getInstance(){return instance;}
    void issue(){count++;
        System.out.println(count);}
    void reset(){count=0;}
    void peek(){
        System.out.println(count);
    }
}
