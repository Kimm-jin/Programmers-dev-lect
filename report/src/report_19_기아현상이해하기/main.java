package report_19_기아현상이해하기;

public class main {
    public static void main(String[] args) {
        ShareResource resource = new ShareResource();

        new WorkerThread(resource, "w1").start();
        new WorkerThread(resource, "w2").start();
        new WorkerThread(resource, "w3").start();

        // 2초마다 자원 공급
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                    resource.makeResourceAvailable();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();

    }
}

// notifyAll로도 완전한 공정성은 보장하지 않는다.
// 다 일어나서 조건 맞는 애들끼리 lock 경쟁