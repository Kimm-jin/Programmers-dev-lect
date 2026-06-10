package java_;
// 새와 고양이는 다르다 - 다형성
public class G_dog extends G_animal{
    public void bark(){
        System.out.println(kind+" dog is barking");
    }

    @Override
    public void walk() {
        System.out.println("투닥투닥");
    }
}
