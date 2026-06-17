package report_17_세마포어게임;


import java.util.concurrent.Semaphore;

public class Dungeon {
    private final Semaphore slots;
    private int capacity;

    public Dungeon(int capacity) {
        this.capacity=capacity;
        this.slots=new Semaphore(capacity);
    }

    public void enter(String name){
        try {
            System.out.println("입장 대기 중...");
            slots.acquire();
            System.out.println(name + "님이 입장하셨습니다. | 남은자리 : "+slots.availablePermits());
            Thread.sleep((int)(Math.random()*100*100)+1000);
            int gold = (int)(Math.random()*100)+1;
            System.out.println("클리어 : "+name+" | 골드 : "+gold);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }finally {
            System.out.println("퇴장 : " +name);
            slots.release();
        }
    }
}
