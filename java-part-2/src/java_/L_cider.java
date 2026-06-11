package java_;

public class L_cider extends L_drink {
    public L_cider() {
        super("사이다", 700);
    }

    @Override
    public void dispense() {
        System.out.println("사이다가 나왔습니다.");
    }
}
