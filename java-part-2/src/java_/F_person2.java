package java_;

public class F_person2 {
    String name;
    int age;

    // 매개변수가 있는 생성자
    public F_person2(String name, int age){
        // name, age -> 지역변수
        // this -> 맴버변수
        // 맴버변수에 지역변수를 대입한다
        this.name=name;
        this.age=age;
    }

    public void display() {
        System.out.println(name + " " + age);
    }
}
