// * 생성자
// 객체가 생성될 때 호출되는 툭수한 메서드이다.
// 생성자는 객체의 초기 상태를 설정하는데 사용되며,
// 클래스 이름과 동일한 이름을 가져야 한다.
// 생성자는 반환 타입이 없으며, 객체가 생성될 때 자동으로 호출된다.


package java_;

public class F_constructor {
    public static void main(String[] args) {
//        기본 생성자를 사용하여 객체 생성
        F_person1 person1 = new F_person1();
        person1.display();

//        매개변수가 있는 생성자를 사용하여 객체 생성
        F_person2 person2 = new F_person2("Paul", 20);
    }
}
