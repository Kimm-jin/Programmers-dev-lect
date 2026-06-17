// * 스레드의 동기화

// * 왜 동기화가 필요할까?
// - 스레드가 1개면 자원을 혼자 쓰니 문제가 없다.
// - 스레드가 여러 개면 같은 자원을 함께 쓰는데, 동시에 건드리면 값이 꼬일 수 있다.

// * 임계 영역(critical section) : 한 번에 한 스레드만 들어가야하는 코드 영역
// - 잠금(lock) : 그 구역에 들어가려면 받아야하는 "열쇠"
// -> 열쇠를 가진 한 스레드만 들어가고, 일을 끝내고 열쇠를 반납해야
// 다음 스레드가 들어갈 수 있다. 그렇지 않다면 DeadLock이 발생 할 수 있다.

// 이렇게 한 스레드의 작업을 다른 스레드가 방해하지 못하게 막는 것을 '동기화'라고 한다.

// * 뮤텍스(상호 배제)
// "열쇠는 단 1개" -> 한 번에 오직 한 스레드만 임계 영역에 들어갈 수 있다.

class ThreadEx_account{
    private int balance = 1000;
    public int getBalance(){
        return balance;
    }

    // 출금
    public void withdraw(int money){
        if (balance >= money){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            balance -= money;
        }
    }
}
// 통장에서 계속 돈을 출금
class ThreadEx_bank implements Runnable{
    private ThreadEx_account account = new ThreadEx_account();

    @Override
    public void run() {

        while (account.getBalance()>0){ // 잔고가 남아 있는 동안 반복
            // 100, 200, 300 중 임의로 선택해서 출금
            int money = (int) (Math.random()*3+1)*100;
            account.withdraw(money);
            System.out.println("blance : "+account.getBalance());
        }
    }
}

public class C_thread_5_sync {
    public static void main(String[] args) {
        Runnable r = new ThreadEx_bank(); // Runnable은 재사용 가능
        new Thread(r).start(); // 출금 스레드2;
        new Thread(r).start(); // 출금 스레드1

    }
}
