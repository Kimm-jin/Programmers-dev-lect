package java_;

/*
    * 자판기
    * 현재 금액과 판매 음료들을 관리한다.
 */


import static java.lang.System.exit;

public class L_vendingmachine {
    private int totalMoney;
    private L_drink[] drinks; // 다형성

    public L_vendingmachine(){
        totalMoney=0;
        drinks=new L_drink[]{
                new L_coke(),
                new L_cider(),
                new L_fanta(),
                new L_water()
        };
    }
    // 돈 넣기 : inserMoney
    public void insertMoney(int money){
        totalMoney+=money;
        System.out.println(totalMoney+"원을 넣었습니다.");
    }
    // 종료 시 잔돈 반환
    public int returnMoney(){
        System.out.println("잔돈을 반환합니다.");
        int tmp = totalMoney;
        totalMoney=0;
        return tmp;
    }
    public void menuPrint(){
        System.out.println("================================= 자판기 ================================");
        System.out.println("[1]콜라-500원 [2]사이다-700원 [3]환타-300원 [4]물-200원 [5]돈넣기 [6]종료");
        System.out.println("현재 금액 : " + totalMoney + "원");
        System.out.println("==========================================================================");
    }

    // 음료구매 : buy - 메뉴 번호(1~4)로 선택
    public void buy(int menuNumber) {
        L_drink drink = drinks[menuNumber - 1];

        if ( totalMoney < drink.getPrice() ) {
            System.out.println("잔돈이 부족합니다.");
            return;
        }

        totalMoney -= drink.getPrice();
        drink.dispense();
    }


}
