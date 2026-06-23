package org.example.springboot.report_25_싱글톤_기본;

public class Settings {
    private static Settings instance;
    private String thema = "dark";
    private Settings(){};
    // 여러 스레드가 동시에 들어오면 객체가 두개 생길 수 있음, 동기화로 막기
    static synchronized Settings getInstance(){
        if(instance==null)instance = new Settings();
        return instance;
    }
    String getThema(){return thema;}
    void setThema(String t){thema=t;}
}
