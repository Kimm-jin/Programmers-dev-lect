package java_;

public class M_fanta implements M_drink{
    private String name = "환타";
    private int price = 300;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPrice() {
        return price;
    }

    @Override
    public void dispense() {
        System.out.println("환타가 나왔습니다.");
    }
}
