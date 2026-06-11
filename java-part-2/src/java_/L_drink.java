package java_;
/*
    * 모든 음료의 공통 틀
    * 이름, 가격
    * 음료가 나올 때의 동작 -> dispense() : 추상 메서드
 */
public abstract class L_drink {
    protected String name; // 같은 패키지 or 상속은 사용가능
    protected int price;

    public L_drink(String name, int price){
        this.name = name;
        this.price = price;
    }

    public String getName(){return this.name;}
    public int getPrice(){
        return this.price;
    }
    public abstract void dispense(); // 음료가 나올 때 동작
}
