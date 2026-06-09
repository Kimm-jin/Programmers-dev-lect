package report_05_숫자업다운게임;

import java.util.InputMismatchException;
import java.util.Scanner;

/*
 시작할 때 범위(1~100)를 안내하는가?
 테스트용 정답 출력을 지웠는가?
 1~100 밖의 숫자를 넣으면 "범위를 벗어났어요" 안내가 나오는가? (선택)
 (선택) 숫자가 아닌 글자를 입력해도 프로그램이 죽지 않는가?
 */
public class updownImpl implements Updown{
    private Scanner sc = new Scanner(System.in);
    private boolean flag=true;
    private int user;
    private int system;
    private int ans;

    @Override
    public void initGame() {    // 정답 정하기
        System.out.println("Com이 숫자를 정했습니다.");

        // 아직 메모리 할당은 안했지만 인스턴스화 예정인 코드기 때문에 프로그램 내부적으로 this가 실행되어 ans를 참조한다.
        this.system = (int)((Math.random()*100)+1);
        return;
    }

    @Override
    public void inpurtGuess() { //  2. 숫자 입력

        while (true){
            try{
                System.out.printf("(범위 1 ~ 100)숫자를 입력해주세요 : ");
                user = this.sc.nextInt();
                this.sc.nextLine();
            }catch (InputMismatchException e){
                System.out.println("문자는 입력할 수 없습니다.");
                this.sc.nextLine();
            }

            if(user<1||user>100){
                System.out.println("범위가 틀렸습니다.");
            }else break;
        }
        this.ans++;
        return;
    }

    @Override
    public void HintMessage() { // 3. 힌트 주기
        if(user>system){
            System.out.println("값이 더 작습니다.");
        } else if (user<system) {
            System.out.println("값이 더 큽니다.");
        }else {System.out.println("정답입니다.");flag=false;}
        return;
    }

    @Override
    public void isCorrect() { //  4. 반복 (2,3)
        while (flag){
            inpurtGuess();
            HintMessage();
        }
    }

    @Override
    public void Count() {
        System.out.println("정답까지 "+ans+"회 걸렸습니다.");
    }
}
