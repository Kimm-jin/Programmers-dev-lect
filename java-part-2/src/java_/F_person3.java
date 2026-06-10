package java_;

public class F_person3 {
    String name;
    int age;

    public F_person3(){
        this.name="Unknown";
        this.age=0;
    }
    public F_person3(String name){
        this.name=name;
        this.age=0;
    }
    public F_person3(String name, int age){
        this.name=name;
        this.age=age;
    }
    public void display() {
        System.out.println(name + " " + age);
    }
}
