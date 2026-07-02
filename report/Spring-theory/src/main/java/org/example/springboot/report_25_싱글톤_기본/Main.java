package org.example.springboot.report_25_싱글톤_기본;

public class Main {
    static void main() {
        NaiveTicketMachine a = new NaiveTicketMachine();
        NaiveTicketMachine b = new NaiveTicketMachine();
        a.issue(); b.issue();
        System.out.println(a==b);
        System.out.println();

//        TicketMachine ticketMachine = new TicketMachine();
        TicketMachine w1 = TicketMachine.getInstance();
        TicketMachine w2 = TicketMachine.getInstance();
        TicketMachine w3 = TicketMachine.getInstance();
        w1.issue();
        w2.issue();
        w3.issue();
        System.out.println(w1==w2);
        System.out.println(w2==w3);
        System.out.println(w1==w3);
        System.out.println();

        w2.reset();
        w1.issue();
        w3.issue();
        w2.peek();

        System.out.println();
        Settings s1 = Settings.getInstance();
        Settings s2 = Settings.getInstance();
        System.out.println(s1.getThema());
        s2.setThema("Light");
        System.out.println(s1.getThema());
        System.out.println(s1==s2);
        System.out.println();

        ENUM e = ENUM.INSTANCE;
        e.issue();
        e.issue();
        e.reset();
        e.issue();

        // 싱글톤 단점
//        여러 코드가 같은 객체를 사용하여 코드 추적이 어려움
//        하나의 객체를 직접 꺼내써서 테스트하기 어렵다

        // IoC/DI가 단점을 어떻게 완화하는가
//        외부 컨테이너가 필요한 객체를 주입하여 실제 실행에선
//        하나의 객체를 재사용 하더라도 테스트 시 테스트에 필요한
//        객체를 주입할 수 있어서 싱글톤의 강한 결합과 테스트를 줄인다

    }
}
