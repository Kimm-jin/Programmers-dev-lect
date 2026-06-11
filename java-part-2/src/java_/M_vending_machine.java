package java_;

public class M_vending_machine {

    private int totalMoney;
    private M_drink[] drinks; // 다형성

    public M_vending_machine(){
        totalMoney=0;
        drinks=new M_drink[]{
                new M_coke(),
                new M_cider(),
                new M_fanta(),
                new M_water()
        };
    }
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
        M_drink drink = drinks[menuNumber - 1];

        if ( totalMoney < drink.getPrice() ) {
            System.out.println("잔돈이 부족합니다.");
            return;
        }

        totalMoney -= drink.getPrice();
        drink.dispense();
    }
}
