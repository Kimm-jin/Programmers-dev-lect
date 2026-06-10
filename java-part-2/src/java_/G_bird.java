package java_;

public class G_bird extends G_animal{
    String wing; // 자식만의 특징 - 부모클래스를 상속받은 후 확장
    public void fly(){
        System.out.println(kind+" is flying " +wing);
    }

    @Override
    public void walk() {
        System.out.println("사뿐사뿐");
    }
}
