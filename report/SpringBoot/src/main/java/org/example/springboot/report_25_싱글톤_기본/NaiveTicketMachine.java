package org.example.springboot.report_25_싱글톤_기본;

public class NaiveTicketMachine {
    private int count;
    void issue(){
        count++;
        System.out.println(count);
    }
}
