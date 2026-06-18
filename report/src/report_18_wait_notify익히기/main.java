package report_18_wait_notify익히기;

public class main {
    public static void main(String[] args) {
        Chat chat1 = new Chat();
//        QuestionThread q1 = new QuestionThread(chat1);
//        QuestionThread q2 = new QuestionThread(chat1);
//        QuestionThread q3 = new QuestionThread(chat1);
//        QuestionThread q4 = new QuestionThread(chat1);
//        AnswerThread a1 = new AnswerThread(chat1);
//        AnswerThread a2 = new AnswerThread(chat1);
//        AnswerThread a3 = new AnswerThread(chat1);
//        AnswerThread a4 = new AnswerThread(chat1);
//
//        q1.start();
//        q2.start();
//        q3.start();
//        q4.start();
//        a1.start();
//
//        a2.start();
//
//        a3.start();
//
//        a4.start();
        QuestionThread q1 = new QuestionThread(chat1);
        AnswerThread a1 = new AnswerThread(chat1);
        q1.start();
        a1.start();

    }
}

/*
    1.  스레드는 순서 보장을 안하며 동기화는 한번에 하나의 스레드만 허용하는데
        wait는 잠들면서 락을 놓기때문에 다른 스레드가 들어와 flag를 바꿀 수 있음
        notify로 깨어난 스레드는 바로 실행되는 게 아니라 다시 락을 얻어야 하고
        깨어났다고 조건이 만족됐다는 보장도 없다.
        if 는 깨어난뒤 무조건 진행하여 순서가 섞일 수 있고
        while은 깨어난 뒤에도 조건을 확인하기때문에 순서대로 진행된다.

    2. sleep은 자는동안 락을 놓지 않음 -> 다른 스레드가 못들어옴 -> 시간 후 다시 조건검사 하고 잔다.
    3. notify - 스레드 하나 꺠우기, notifyAll - 스레드 모두 꺠우기

 */