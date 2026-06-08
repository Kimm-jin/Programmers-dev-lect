package report_03_빙고게임;
/*
- 나와 컴퓨터가 각자 1~25 숫자가 **무작위로 섞인** 빙고판을 가진다.
- 내 차례에 숫자 하나를 부르면, **나와 컴퓨터 두 판 모두**에서 그 숫자가 지워진다.
- 이어서 컴퓨터가 무작위로 숫자 하나를 부른다. (역시 두 판 모두 지워짐)
- 가로·세로·대각선 한 줄이 모두 지워지면 **빙고 1줄**.
- 먼저 목표 줄 수(기본 3줄)를 완성하는 쪽이 **승리**.
 */

import java.util.*;

import static java.lang.System.exit;


public class bingo {
    // myBingo, comBingo
    // myCnt, comCnt
    public static int myShuffle[] = new int[25];
    public static int comShuffle[] = new int[25];
    public static int myBingo[][] = new int[5][5];
    public static int comBingo[][] = new int[5][5];
    public static int visited[] = new int[26];
    public static int myCnt, comCnt;


    //1. 빙고 생성
    //  1-1 1차원배열 길이가 25인 배열에 1~25를 저장하고 랜덤함수 실행
    //  1-2 swap으로 중복없이 난수 생성
    //  1-3 섞인 1차원배열을 2차원 배열에 넣는다
    public static void createBingo() {
        for (int i = 0; i < myShuffle.length; i++) myShuffle[i] = i + 1;
        for (int i = 0; i < comShuffle.length; i++) comShuffle[i] = i + 1;
        for (int i = 1; i <= 25; i++) visited[i] = 0;

        Random r = new Random();
        for (int i = 0; i < myShuffle.length; i++) {
            int val = r.nextInt(myShuffle.length);

            int tmp = myShuffle[i];
            myShuffle[i] = myShuffle[val];
            myShuffle[val] = tmp;
        }
        int idx = 0;
        for (int i = 0; i < myBingo.length; i++) {
            for (int j = 0; j < myBingo[i].length; j++) {
                myBingo[i][j] = myShuffle[idx++];
            }
        }


        for (int i = 0; i < comShuffle.length; i++) {
            int val = r.nextInt(comShuffle.length);

            int tmp = comShuffle[i];
            comShuffle[i] = comShuffle[val];
            comShuffle[val] = tmp;
        }
        idx = 0;
        for (int i = 0; i < comBingo.length; i++) {
            for (int j = 0; j < comBingo[i].length; j++) {
                comBingo[i][j] = comShuffle[idx++];
            }
        }
    }


    //2. 빙고 진행
//    2-1 규격에 맞게 출력
//    2-2 입력된 숫자(나와 컴퓨터)가 범위,type 이전에 부른 숫자와 중복되지 않는지 확인
//    2-3 뽑힌 숫자는 0처리
    public static void startGame() {
        Scanner sc = new Scanner(System.in);
        // 빙고판 보여주기(내꺼만)
        System.out.println("====== 내 빙고판 ======");
        for (int i = 0; i < myBingo.length; i++) {
            for (int j = 0; j < myBingo[i].length; j++) {
                if(myBingo[i][j]==0){
                    System.out.printf("[ ★] ");
                }else{
                    System.out.printf("[%2d] ", myBingo[i][j]);
                }
            }
            System.out.printf("\n");
        }
        int comNum;
        int myNum;
        while (true) {
            try {
                System.out.println("부를 숫자 입력 (1~25) > ");
                myNum = sc.nextInt();
                sc.nextLine();

                // 숫자 범위, 이미 불려진 숫자인지 check 예외처리
                if (visited[myNum] == -1 || myNum > 25 || myNum < 1) {
                    System.out.println("이미 불렸거나 잘못된 숫자입니다. 다시 입력하세요");
                    continue;
                }
                visited[myNum] = -1;

                while (true) {
                    comNum = (int) ((Math.random() * 25) + 1);
                    if (visited[comNum] == -1) { // 이미 뽑힌 숫자
                        continue; // 다시 뽑기
                    } else {
                        visited[comNum] = -1; // 처음 뽑혀 방문처리
                        break;
                    }
                }
                break;
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("범위 초과");
            }catch (InputMismatchException e){
                System.out.println("문자는 입력할 수 없습니다.");
                sc.nextLine(); // 버퍼 안비우면 해당글자가 남아 무한루프에 빠짐
            }

        }


        System.out.printf("▶ 내가 부른 숫자: %d\n", myNum);
        System.out.printf("▶ 컴퓨터가 부른 숫자: %d\n", comNum);


        for (int i = 0; i < myBingo.length; i++) {
            for (int j = 0; j < myBingo[i].length; j++) {
                if (myBingo[i][j] == myNum || myBingo[i][j] == comNum) {
                    myBingo[i][j] = 0;
                }
                if (comBingo[i][j] == myNum || comBingo[i][j] == comNum) {
                    comBingo[i][j] = 0;
                }
            }
        }

    }
//3. 빙고 확인
//    3-1 행 확인
//    3-2 열 확인
//    3-3 대각선 확인

    public static void bingoCheck() {
        // 행 check
        myCnt = 0;
        comCnt = 0;
        int myCol = 0;
        int comCol = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (myBingo[j][i] == 0) myCol++;
                if (comBingo[j][i] == 0) comCol++;
            }
            if (myCol == 5) {
                myCnt++;
            }
            if (comCol == 5) {
                comCnt++;
            }
            myCol = 0;
            comCol = 0;
        }
        // 열 check
        int myRow = 0;
        int comRow = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (myBingo[i][j] == 0) myRow++;
                if (comBingo[i][j] == 0) comRow++;
            }
            if (myRow == 5) {
                myCnt++;
            }
            if (comRow == 5) {
                comCnt++;
            }
            myRow = 0;
            comRow = 0;
        }

        // 대각선 좌상 -> 우하,    좌하 -> 우상
        int myDiag1 = 0;
        int comDiag1 = 0;
        int myDiag2 = 0;
        int comDiag2 = 0;
        for (int i = 0; i < 5; i++) {
            if (myBingo[i][i] == 0) myDiag1++;
            if (comBingo[i][i] == 0) comDiag1++;

            if (myBingo[4 - i][i] == 0) myDiag2++;
            if (comBingo[4 - i][i] == 0) comDiag2++;


        }
        if (myDiag1 == 5) myCnt++;
        if (myDiag2 == 5) myCnt++;
        if (comDiag1 == 5) comCnt++;
        if (comDiag2 == 5) comCnt++;


        System.out.println("내 빙고 수 : " + myCnt);
        System.out.println("Com 빙고 수 : " + comCnt);
        System.out.println();
    }

    //4. 승리 확인
//    4-1 매 턴 빙고카운트가 3또는 그 이상이라면 승리
    public static void winner() {
        if (myCnt >= 3 && comCnt >= 3) {
            System.out.println("비겼습니다.");
            view();
            exit(0);

        } else if (myCnt >= 3 && comCnt < 3) {
            System.out.println("플레이어가 이겼습니다.");
            view();
            exit(0);

        } else if (myCnt < 3 && comCnt >= 3) {
            System.out.println("Computer가 이겼습니다.");
            view();
            exit(0);
        }
    }

    public static void view() {
        System.out.println("========My bingo========");
        for (int i = 0; i < myBingo.length; i++) {
            for (int j = 0; j < myBingo[i].length; j++) {
                if(myBingo[i][j]==0){
                    System.out.printf("[ ★] ");
                }else{
                    System.out.printf("[%2d] ", myBingo[i][j]);
                }
            }
            System.out.println("");
        }
        System.out.println("");
        System.out.println("========Com bingo========");
        for (int i = 0; i < comBingo.length; i++) {
            for (int j = 0; j < comBingo[i].length; j++) {
                if(comBingo[i][j]==0){
                    System.out.printf("[ ★] ");
                }else{
                    System.out.printf("[%2d] ", comBingo[i][j]);
                }
            }
            System.out.println("");
        }
        System.out.println("");
    }


    public static void main(String[] args) {
        createBingo();
        while (true) {
            startGame();
            bingoCheck();
            winner();
        }

    }
}

/*
O    1~25가 겹치지 않고 무작위로 배치되는가?
O   같은 숫자를 두 번 부를 수 없는가? (나/컴퓨터 모두)
O    숫자 입력란에 글자를 넣어도 프로그램이 죽지 않는가?
O    가로·세로·대각선 줄이 정확히 세어지는가?
O    양쪽이 동시에 목표 줄에 도달하면 무승부 처리가 되는가?
O    게임이 끝나면 양쪽 판을 모두 보여주는가?
 */