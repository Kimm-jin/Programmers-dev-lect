// * 데몬 스레드
// - 메인 작업을 "뒤에서 도와주는" 보조 스레드
// - 일반 스레드가 전부 끝나면, 데몬 스레드도 같이 종료된다.

// * 실제
// - 가비지 컬렉터(GC), 워드의 자동저장

import java.util.TreeSet;

public class C_thread_4_daemon implements Runnable {

    static boolean autoSave = false;
    // 3초 마다 한 번씩 "자동저장이 켜져 있으면" 저장을 실행
    // 데몬스레드라서 main이 끝나면 함께 자동 저장된다.
    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(3*1000); // 3초
                if(autoSave){
                    autoSave();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void autoSave(){
        System.out.println("작업 파일이 자동 저장되었습니다.");
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new C_thread_4_daemon());
        thread.setDaemon(true); // 시작 전 데몬스레드를 켜줘야한다.
        thread.start();
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println(i);
            if(i==5)autoSave=true;
        }

        System.out.println("프로그램을 종료합니다.");
    }


}
/*
1. main이 시작된다.
2. 자동저장용 스레드를 만든다.
3. thread.setDaemon(true)로 보조 스레드로 만든다.
4. thread.start()로 자동저장 스레드가 시작된다.
5. main은 1초마다 숫자를 출력한다.
6. i == 5일 때 autoSave가 true가 된다.
7. 데몬 스레드는 3초마다 autoSave를 확인하고 저장 메시지를 찍는다.
8. main이 끝나면 데몬 스레드는 while(true)여도 같이 종료된다.
 */