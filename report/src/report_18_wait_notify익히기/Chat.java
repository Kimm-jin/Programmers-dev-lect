package report_18_wait_notify익히기;

public class Chat {
    private boolean flag;
    private int bradeCnt;

    // flag로 답변 전인지 후인지 판단 - true(답변 차례), false(질문 차례)
    // while(true)
    // 질문 - 답변 1:1관계로 진행되어야 하기 떄문에 동기화
    // sleep - lock상태로 잠듬 -> 다른 스레드가 못들어옴
    // wait - unlock상태로 잠듬 -> 다른 스레드가 들어올 수 있음
    // notify - wait로 기다리는 스레드 하나를 깨움
    // interrupt - 자는 스레드에게 중단,깨우기 요청
    public synchronized void question(String msg) {
        while (flag) {
            try {
                wait();
//                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Question : " + msg);
        flag = true;
        notifyAll();
    }

    public synchronized void answer(String msg) {
        while (!flag) {
            try {
                wait(2000);
//                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Answer : " + msg);
        flag = false;
        notifyAll();
    }


    public synchronized void makeBrade(String msg) {
        while (bradeCnt > 0) {
            try {
                wait(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        bradeCnt++;
        System.out.println("빵 만듬 : " + bradeCnt);
        notifyAll();
    }

    public synchronized void eatting(String msg) {
        while (bradeCnt<1) {
            try {
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        bradeCnt--;
        System.out.println("빵 먹음 : " + bradeCnt);
        notifyAll();
    }
}
