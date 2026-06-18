package report_18_wait_notify익히기;

public class QuestionThread extends Thread{
    private Chat chat;
    private String question[]= {"질문1","질문2","질문3"};
    public QuestionThread(Chat chat){this.chat=chat;}

    @Override
    public void run() {
        super.run();
        for (int i = 0; i < question.length; i++) {
//            chat.question(question[i]);
            chat.makeBrade(question[i]);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
