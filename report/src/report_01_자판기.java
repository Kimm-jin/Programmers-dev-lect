// 자판기

import java.util.Scanner;
import static java.lang.System.exit;

public class report_01_자판기 {
    static final int COKE = 500, CIDER = 700, FANTA = 300, WATER = 200;
    static int Total = 0;
    public static void printMenu(int totalMoney) {
        System.out.println("================================= 자판기 ================================");
        System.out.println("[1]콜라-500원 [2]사이다-700원 [3]환타-300원 [4]물-200원 [5]돈넣기 [6]종료");
        System.out.println("현재 금액 : " + totalMoney + "원");
        System.out.println("==========================================================================");
    }
    static  int button(){
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }


    static void drink(int val){
        if(val==1){
            if(Total>=COKE){
                Total-=COKE;
                System.out.println("콜라 구매");
                return;
            }else {
                System.out.println("콜라를 구매할 잔액이 부족합니다");
                return;
            }
        }
        else if(val==2){
            if(Total>=CIDER){
                Total-=CIDER;
                System.out.println("사이다 구매");
                return;
            }else {
                System.out.println("사이다를 구매할 잔액이 부족합니다");
                return;
            }


        }
        else if(val==3){
            if(Total>=FANTA){
                Total-=FANTA;
                System.out.println("환타 구매");
                return;
            }else {
                System.out.println("환타를 구매할 잔액이 부족합니다");
                return;
            }

        }
        else if(val==4){
            if(Total>=WATER){
                System.out.println("물 구매");
                Total-=WATER;
                return;
            }else {
                System.out.println("물을 구매할 잔액이 부족합니다");
                return;
            }
        }
    }
    static void Money(){
        Scanner sc = new Scanner(System.in);
        Total += sc.nextInt();

    }




    public static void main(String[] args) {
        for(;;) {
            printMenu(Total);
            int button=0;
            if(Total==0){
                System.out.printf("현재 잔엑이 %d원 입니다 돈을 넣어주세요\n", Total);
                button=5;
            }else{
                button = button();
            }
            if (button <= 0 || button > 6) {
                System.out.println("잘못된 접근입니다.");
                exit(0);
            } else if (button == 5) {
                System.out.printf("금액을 입력하세요 : ");
                Money();
            } else if (button < 5) {
                drink(button);
            } else if(button==6){ // 6
                System.out.printf("반환 금액 : %d", Total);
                exit(0);
            }
        }


    }

}
