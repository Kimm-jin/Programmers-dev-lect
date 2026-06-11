// * 다형성
// 동일한 인터페이스나 부모 클래스를 공유하는 여러 객체들이 각자 다른 방식으로 동작하도록 할 수 있는 기능을 말한다.
// 다형성은 "하나의 인터페이스로 여러 형태의 객체를 처리할 수 있다"는 의미를 가지고 있다.
// 이를 통해 코드의 유연성과 확장성을 크게 향상시킬 수 있다.



package java_;
class K_animal{
    public void sound(){
        System.out.println("Animal sound");
    }
}
class K_dog extends K_animal{
    public void fetch(){
        System.out.println("Dog fetched a ball");
    }
    @Override
    public void sound() {
        super.sound();
        System.out.println("Dog sound");
    }
}
class K_cat extends K_animal{
    @Override
    public void sound() {
        super.sound();
        System.out.println("Cat sound");
    }
}
public class K_polymorphism {
    public static void main(String[] args) {
        K_animal myAnimal = new K_animal();
        K_dog d = new K_dog();
        //        myAnimal.sound();
//        d.sound();
//        d.fetch();

        //부모는 자식을 받아올 수 있음.
        K_animal myDog = new K_dog(); // Dog타입의 객체, Animal타입으로 업캐스팅(형변환)
        K_animal myCat = new K_cat(); // Cat타입의 객체, Animal타입으로 업캐스팅(형변환)

        myAnimal.sound();
        myDog.sound();  // 런타임 시점(시작부터 결과를 출력할때까지) Dog의 sound()호출
        myCat.sound();  // 런타임 시점(시작부터 결과를 출력할때까지) Cat의 sound()호출
        // 컴파일 시점은 코드작성 중 IDE에서 제공하는 빨간줄 등.

        // 다운캐스팅은 명시적으로 적어줘야함.
//        K_dog myDog2 = (K_dog)new K_animal(); // 실직적 인스턴스는 animal이라 -> dog으로 캐스팅 안됨(x)
        K_dog myDog2 = (K_dog)myDog; // 실질적인 인스턴스가 dog이기 때문에 다운캐스팅 가능
        myDog2.fetch();
    }
}
