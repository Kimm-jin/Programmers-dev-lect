package org.example.springboot.report_25_싱글톤_기본;

public enum ENUM {
    INSTANCE;

    private int count = 0;

    void issue(){count++;
        System.out.println(count);}
    void reset(){count=0;}
}
