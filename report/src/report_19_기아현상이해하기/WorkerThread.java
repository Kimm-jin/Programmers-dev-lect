package report_19_기아현상이해하기;

public class WorkerThread extends Thread{
    private ShareResource resource;
    private String name;
    public WorkerThread(ShareResource resource, String name){
        this.resource=resource; this.name=name;
    }

    @Override
    public void run() {
        super.run();
        while (true){
            resource.waitForResource(name);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
