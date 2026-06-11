package java_;

// * 자판기 시작점

import java.util.Scanner;

public class L_start {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        L_vendingmachine vendingmachine = new L_vendingmachine();


        while (true){
            vendingmachine.menuPrint();
            System.out.println("선택");
            int choice = sc.nextInt();
            sc.nextLine();

            if(choice<5&&choice>0){
                vendingmachine.buy(choice);
            }else if(choice==5){
                System.out.printf("돈 넣기 : ");
                int money = sc.nextInt();
                vendingmachine.insertMoney(money);
            }else if(choice==6){
                System.out.println("잔돈 : "+vendingmachine.returnMoney());
                return;
            }else{
                System.out.println("잘못 입력하셨습니다. 다시 입력해주세요");
            }
        }

    }
}
