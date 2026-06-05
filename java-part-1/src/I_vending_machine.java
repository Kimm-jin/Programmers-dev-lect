import java.util.Scanner;

public class I_vending_machine {

    // [요구사항]
    // 돈 넣기
    // 메뉴선택 시 -> 음료가 나온다, 돈이 차감된다.
    // 종료시 잔돈이 반환된다.

    static final int COKE = 500, CIDER = 500, FANTA = 300, WATER = 200;

    // 사용자 메뉴가 출력 -> 현재 잔금도 표시
    public static void printMenu(int totalMoney) {
        System.out.println("============== 자판기 ==============");
        System.out.println("[1]콜라 : 500, [2]사이다 : 500, [3]환타 : 300, [4]물 : 200, [5]돈 넣기, [6]종료");
        System.out.println("현재 금액 : " + totalMoney);
        System.out.println("====================================");
    }

    // 사용자로부터 메뉴 번호를 받는 함수
    public static int getChoice() {
        // 내용완성
        Scanner sc = new Scanner(System.in);
        System.out.println("원하는 메뉴를 선택하시오.");

        return sc.nextInt();
    }

    static void main(String[] args) {

        int totalMoney = 0;

        while ( true ) {
            printMenu(totalMoney);
            // 사용자 주문번호
            int choice = getChoice();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    System.out.println("\n잔돈 " + totalMoney + "원이 반환되었습니다.");
                    return;
                default:
                    System.out.println("잘 못 입력하셨습니다. 다시 입력해주세요.");
                    break;
            }
        }

    }

}
