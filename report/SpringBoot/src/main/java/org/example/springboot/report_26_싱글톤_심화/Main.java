package org.example.springboot.report_26_싱글톤_심화;

public class Main {
    static int badMismatch = 0;
    static int goodMismatch = 0;

    public static void main(String[] args) throws InterruptedException {
        int N = 30;

        Thread[] t1 = new Thread[N];

        for (int i = 0; i < N; i++) {
            final String myName = "손님" + i;
            t1[i] = new Thread(() -> {
                String r = GreetingServiceBad.getInstance().greet(myName);
                if (!r.equals(myName)) {
                    synchronized (Main.class) {
                        badMismatch++;
                    }
                }
            });
        }

        for (Thread t : t1) t.start();
        for (Thread t : t1) t.join();

        Thread[] t2 = new Thread[N];

        for (int i = 0; i < N; i++) {
            final String myName = "손님" + i;
            t2[i] = new Thread(() -> {
                String r = GreetingServiceGood.getInstance().greet(myName);
                if (!r.equals(myName)) {
                    synchronized (Main.class) {
                        goodMismatch++;
                    }
                }
            });
        }

        for (Thread t : t2) t.start();
        for (Thread t : t2) t.join();

        System.out.println("[필드에 저장] 엉킴 : " + badMismatch + " / " + N);
        System.out.println("[파라미터로]  엉킴 : " + goodMismatch + " / " + N);

        System.out.println();
        Dao dao1 = Dao.getInstance();
        Dao dao2 = Dao.getInstance();

        System.out.println(dao1.findUser("kim"));
        System.out.println(dao2.findUser("lee"));
        System.out.println("같은 DAO인가? " + (dao1 == dao2));
    }
}