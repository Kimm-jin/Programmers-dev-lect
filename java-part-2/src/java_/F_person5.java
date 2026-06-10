package java_;

public class F_person5 {
    String name;
    int age;
//    F_person5("john",25); // 메모리 할당이 되지 않은 생성자
//    this는 인스턴스 예정인 코드라 사용 가능
//    위처럼 접근하려면 static선언을 해 메서드 영역에 메모리할당을 해줘야한다.
    public F_person5(){
        this("john",25);
    } // 아래 생성자를 호출한다.
    public F_person5(String name, int age){
        this.name=name;
        this.age=age;
    }

    public void diplay(){
        System.out.println(name+" "+age);
    }
}
