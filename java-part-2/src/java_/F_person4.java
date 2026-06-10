package java_;

public class F_person4 {
    String name;
    int age;

    public F_person4(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //복사 생성자
    public F_person4(F_person4 other) { // 깊은 복사
        this.name = other.name;
        this.age = other.age;
    }

    public void diplay() {
        System.out.println(name + " " + age);
    }
}
