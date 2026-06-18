package report_18_wait_notify익히기;

public class AnswerThread extends Thread{
    private Chat chat;
    private String answer[] = {"답변1","답변2","답변3"};
    public AnswerThread(Chat chat){this.chat=chat;}

    @Override
    public void run() {
        super.run();
        for(String a : answer){
//            chat.answer(a);
            chat.eatting(a);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
