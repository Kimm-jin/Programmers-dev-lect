
// * 반복문
// 어떤 작업이 반복적으로 수행되도록 할 때 사용된다.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class H_loop {

    // 2~9단출력
    static void practice1() {
        for (int i = 2; i <= 9; i++) {
            for (int j = 1; j <= 9; j++) {
                System.out.printf("%d * %d = %d\n", i, j, i * j);
            }
        }
    }

    // 1~100 짝수만 출력 (continue)
    static void practice2() {
        for (int i = 1; i < 101; i++) {
            if (i % 2 == 0) {
                System.out.println(i);
                continue;
            }
        }
    }

    // break
    static void practice3() {
        for (int i = 1; i < 101; i++) {
            if (i == 30) break;
            System.out.println(i);
            continue;
        }
    }

    // 9~2단 역순출력
    static void practice4() {
        for (int i = 9; i >= 2; i--) {
            System.out.printf("%d단\n", i);
            for (int j = 1; j <= 9; j++) {
                System.out.printf("%d * %d = %d\n", i, j, i * j);
            }
        }
    }

    // while문으로 2단출력
    static void practice5() {
        int cnt = 2;
        int val = 1;
        while (val <= 9) {
            System.out.printf("%d * %d = %d\n", cnt, val, cnt * val);
            val++;
        }
    }

    // while문으로 2~9단 출력
    static void practice6() {
        int i = 2;

        while (i <= 9) {
            System.out.println(i + "단");
            int j = 1;
            while (j <= 9) {
                System.out.printf("%d * %d = %d\n", i, j, i * j);
                j++;
            }
            i++;
        }
    }

    // 정수 입력 후 1이될때까지 더한다.
    // 입력값이 0이면 탈출
    static void practice7() {
        Scanner sc = new Scanner(System.in);
        System.out.printf("정수를 입력하세요 : ");
        while (true) {
            int num = sc.nextInt();
            if (num == 0) {
                System.out.println("0을 입력하셨습니다. 종료합니다");
                break;
            }
            int sum = 0;
            for (int i = num; i >= 1; i--) {
                sum += i;
            }
            System.out.printf("결과 : %d\n", sum);
        }
    }

    // 입력한 단부터 9단까지, 0누르면 종료
    static void practice8() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.printf("0누르면 종료 값 입력 : ");
            int num = sc.nextInt();
            if (num == 0) {
                System.out.println("0을 입력하였습니다. 종료합니다");
                break;
            }
            for (int i = num; i <= 9; i++) {
                System.out.printf("%d단\n", i);
                for (int j = 1; j <= 9; j++) {
                    System.out.printf("%d * %d = %d\n", i, j, i * j);
                }
            }

        }
    }
    static void practice9(){
        int num=0;
        while(num!=0){
            System.out.println("while");
        }
        // do-while은 최초 한번은 조건 상관없이 무조건 실행됨
        do {
            System.out.println("do-while");
        }while(num!=0);
    }

    public static void main(String[] args) {
        //practice1();
        //practice2();
        //practice3();
        //practice4();
        //practice5();
        //practice6();
        //practice7();
        //practice8();
        //practice9();


        List<List<Integer>> matrix = new ArrayList<>();

        // [준비 단계] 먼저 3개의 빈 행(상자)을 대형 상자에 add해줍니다. (이게 핵심!)
        for (int i = 0; i < 3; i++) {
            matrix.add(new ArrayList<>());
        }

        // [대입 단계] 이제 2중 for문을 돌며 값을 바로바로 대입(추출 및 추가)합니다.
        int value = 1;
        for (int i = 0; i < 3; i++) {       // i는 행 (0~2)
            for (int j = 0; j < 4; j++) {   // j는 열 (0~3)
                // i번째 행 상자를 꺼내서(get), 거기다가 value를 추가(add)한다!
                matrix.get(i).add(value);
                value++;
            }
        }

        // 전체 출력하여 확인
        System.out.println("2중 for문 결과: " + matrix);


    }
}
