// * spuer
// super는 자식 클래스에서 조상 클래스로부터 상속받은 맴버를 참조하는데 사용되는 참조 변수이다.
// 맴버변수와 지역변수 이름이 같을 때 this를 붙여 구별하였듯이 상속받은 맴버와(부모맴버) 자신의 맴버가 이름이 같을 때는
// super를 붙여 구별할 수 있다.
// this = 맴버변수와 매개변수의 이름이 같을 때,  super = 부모 맴버변수와 자식 맴버변수 이름이 같을 때
// 부모 클래스로부터 상속받은 맴버도 자식 클래스 자신의 맴버이므로 super대신 this를 사용할 수 있다.
// 그래도 조상 클래스의 맴버와 자식클래스의 맴버가 중복 정의되어 서로 구별해야하는 경우에만 super를 사용하는게 좋다.
// 조상의 맴버와 자신의 맴버를 구별하는데 사용된다는 점을 제외하고는 super와 this는 근본적으로는 같다.
// 모든 인스턴스에는 자신이 속한 인스턴스와 주소가 지역변수로 지정되는데,
// 이것이 참조변수인 this와 super의 값이 된다.
// static 메서드 인스턴스와 관련이 없다. 그래서 this와 super는 static메서드에서는 사용할 수 없고
// 인스턴스에서만 사용할 수 있다.

package java_;

class SuperParent{
    int x=10;
    int y=20;
    public String getLocation(){
        return "x = "+x+", y = " + y;
    }
}
class SuperChild extends SuperParent{
    int y=30;
    int z=40;
    void method(){
        System.out.println("x = " + x);
        System.out.println("ths.x = "+this.x);
        System.out.println("super.x = "+super.x);

        System.out.println("y = " + y);
        System.out.println("ths.y = "+this.y);
        System.out.println("super.y = "+super.y);
    }

    @Override
    public String getLocation() {
        return super.getLocation() + ", z = " + z;
    }
}

public class I_super {
    public static void main(String[] args) {
        SuperParent superParent = new SuperParent();
        SuperChild superChild = new SuperChild();
        superChild.method();
        System.out.println(superParent.getLocation());
        System.out.println(superChild.getLocation());
    }
}
