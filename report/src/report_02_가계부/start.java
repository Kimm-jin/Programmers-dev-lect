package report_02_가계부;/*
Collection 활용

1	내역 추가	날짜를 입력하고, 그 날짜에 항목(이름 + 금액)을 여러 개 등록한다. 등록이 끝나면 합계를 보여준다.
2	내역 조회	지금까지 기록한 날짜 목록을 보여준다. 사용자가 날짜 하나를 입력하면 그 날의 항목들과 합계를 보여준다.
3	전체 삭제	날짜를 입력받아 그 날짜와 모든 항목을 통째로 삭제한다.
4	내역 삭제	날짜를 입력받아 항목들을 보여주고, 그중 특정 항목 하나만 삭제한다.
5	종료	프로그램을 끝낸다.

Map<String, List<report_02_가계부.Item>>
      │           │
   날짜 문자열   그 날의 항목들(List)
 */

import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import static java.lang.System.exit;


public class start {
    public static void main(String[] args) {
        /*
            Scanner로 번호를 입력받는다.
            while 반복문 안에서 메뉴를 출력한다.
            switch 또는 if로 번호를 구분한다.
            5를 입력하면 반복을 멈추고 "프로그램을 종료합니다" 출력.
         */
        Scanner sc = new Scanner(System.in);
        Map<String, List<Item>> map = new HashMap<>();
        AccountBook book = new AccountBookImpl();

        while(true){
            System.out.println("==== 가계부 ====");
            System.out.println("1. 내역 추가");
            System.out.println("2. 내역 조회");
            System.out.println("3. 전체 삭제");
            System.out.println("4. 내역 삭제");
            int idx = Integer.parseInt(sc.nextLine());
            switch (idx){
                case 1 : {
                    book.addAccount(); // 내역 추가
                    break;
                } case 2 : {
                    book.showAccount(); // 내역 조회
                    break;
                } case 3 : {
                    book.deleteAll(); // 전체 삭제
                    break;
                } case 4 : {
                    book.deleteItem(); // 내역 삭제
                    break;
                } case 5 : {
                    System.out.println("종료합니다");
                    exit(0);
                } default:
                    System.out.println("잘못된 번호입니다.");
            }
        }

    }
}

/*
 O   합계가 모든 조회/추가 화면에서 정확히 계산되는가?
 O   기록이 하나도 없을 때 조회/삭제하면 안내 메시지가 나오는가?
 O   없는 날짜를 입력했을 때 프로그램이 멈추지 않는가?
 O  숫자 입력란에 글자를 넣어도 죽지 않는가? (예외 처리)
 O  메뉴에서 1~5 외의 번호를 넣으면 "잘못된 번호" 안내가 나오는가?
 */