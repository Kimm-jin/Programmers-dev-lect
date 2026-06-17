package report_16_스레드실행제어익히기;

import java.util.Scanner;

class thread_1 extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 300; i++)
            System.out.printf("%s", "-");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("종료");
    }
}

class thread_1_1 extends Thread {
    @Override
    public void run() {
        super.run();

        for (int i = 0; i < 300; i++)
            System.out.printf("%s", "|");
    }
}

//  interrupt 호출하면, busy for가 끝난 뒤 멈춤
class thread_3 extends Thread {
    @Override
    public void run() {
        super.run();
        int i = 10;
        while (i != 0 && !isInterrupted()) {
            System.out.println(i--);
            for (long j = 0; j < 5_000_000_000L; j++) ;
        }
        System.out.println("종료");

    }
}

// sleep -> interrupt
class thread_4 extends Thread {
    @Override
    public void run() {
        super.run();
        int i = 10;
        while (i != 0 && !isInterrupted()) {
            System.out.println(i--);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                System.out.println("자다 일어남");
                break;
            }

        }
        System.out.println("종료");

    }
}

class thread_5 extends Thread{
    private String name;
    public thread_5(String name){this.name=name;}

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 5; i++) {
            System.out.println(name+ " : " + i);
            Thread.yield(); // cpu 양보(권유)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}



public class main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // step1
//        thread_1 t1 = new thread_1();
//        thread_1_1 t2 = new thread_1_1();
//        t1.start(); t2.start();

        // step2
//        thread_1 t3 = new thread_1();
//        try {
//            // t1을 재워라 X
//            // 현재 이 코드를 실행 중인 스레드를 재워라 O
//            // 이 코드를 실행중인것은 main 스레드.
//            t3.sleep(2000); // == Thread.sleep(2000)
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("ㅇㅇ");

        // step3
//        thread_3 t4 = new thread_3();
//        t4.start();
//        sc.nextLine();
//        t4.interrupt();


        // step4
//        thread_4 t5 = new thread_4();
//        t5.start();
//        sc.nextLine();
//        t5.interrupt();

        // step5
//        thread_5 t6 = new thread_5("스레드1");
//        t6.start();
//        new thread_5("스레드2").start();

        // step6
        thread_1 t1 = new thread_1();
        thread_1_1 t2 = new thread_1_1();
        t1.start();t2.start();
        long start = System.currentTimeMillis();
        try {
            // join - 다른 스레드가 끝나길 기다리기
            t1.join(); // main 대기
            t2.join(); // main 대기
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("소요시간 : "+(System.currentTimeMillis() - start)+"ms");
        // join이 없으면 t1,t2스레드가 끝나기 전 메인 스레드가 종료된다.
    }
}


/*

1. start()는 NEW 상태의 스레드를 RUNNABLE 상태로 만든다.

2. sleep()은 static 메서드라 특정 객체가 아니라 현재 실행 중인 스레드에 작동한다.
   그래서 main에서 t1.sleep()을 해도 t1이 아니라 main이 잔다.

3. interrupt()는 강제 종료가 아니라 멈춰달라는 요청이다.
   스레드가 isInterrupted()를 확인해야 멈출 수 있다.

4. sleep 중인 스레드에 interrupt()를 걸면 InterruptedException이 발생하며 깨어난다.

5. yield()는 CPU 양보 힌트일 뿐이고 실행 순서를 보장하지 않는다.

6. join()은 join을 호출한 스레드가 대상 스레드가 끝날 때까지 기다리게 한다.
   main에서 t1.join()을 호출하면 main이 t1 종료까지 기다린다.

 */