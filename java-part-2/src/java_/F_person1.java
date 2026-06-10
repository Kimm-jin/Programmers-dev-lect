package java_;

public class F_person1 {
    // 맴버 변수 = 필드
    String name;
    int age;

    // 생성자
    // 반환 타입이 없고 클래스와 이름이 같아야 한다.
    public F_person1() {
        System.out.println("기본 생성자");
        name = "Paul";
        age = 20;
    }

    // 초기화 확인용
    public void display() {
        System.out.println(name + " " + age);
    }
}
