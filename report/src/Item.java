// 항목 1개를 표현하는 클래스(이름, 금액)
public class Item {
    private String name;
    private int price;

    public Item(String name, int price) {
        // this.name(좌변) - 현재 이 객체 힙 영역에 생성된 진짜 맴버 변수
        // name(우변) 괄호안에 있는 파라미터값
        this.name = name;
        this.price = price;
    }
    public String getName()  { return name; }
    public int getPrice()    { return price; }
}
