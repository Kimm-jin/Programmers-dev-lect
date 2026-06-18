package report_19_기아현상이해하기;

public class ShareResource {
    boolean isAvailable = false;

    public synchronized void waitForResource(String threadName){
        while (!isAvailable){
            try {
                System.out.println(threadName+ " is waiting for resource...");
                wait();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        isAvailable=false;
        System.out.println("got the resource!");
    }

    public synchronized void makeResourceAvailable(){
        isAvailable=true;
        System.out.println("Resource is now available!");
//        notify();
        notifyAll();
    }

}
