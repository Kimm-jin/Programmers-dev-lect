// * 싱글스레드 vs 멀티스레드

class C_thread3_1 extends Thread{
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 300; i++) {
            System.out.printf("%s ", "|");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("소요시간2 : " + (endTime - C_thread_3.startTime + "ms"));
    }


}

public class C_thread_3 {

    static long startTime = 0;

    // - 싱글스레드
    public static void exam1(){
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 300; i++) {
            System.out.printf("%s","-");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("소요시간1 : " +(endTime-startTime)+"ms");
        for (int i = 0; i < 300; i++) {
            System.out.printf("%s","|");
        }
        endTime = System.currentTimeMillis();
        System.out.println("소요시간2 : " +(endTime-startTime)+"ms");
    }
    // - 멀티스레드
    public static void exam2(){
        // 3_1스레드가 실행
        C_thread3_1 thread = new C_thread3_1();
        thread.start();
        startTime=System.currentTimeMillis();
        // main스레드가 출력
        for (int i = 0; i < 300; i++) {
            System.out.printf("%s","-");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("소요시간1 : " + (endTime - startTime)+"ms");
    }
    public static void main(String[] args) {

    }
}
