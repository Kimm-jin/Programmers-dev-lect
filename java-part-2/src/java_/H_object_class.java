// & Object 클래스 - 모든 클래스의 조상 / java.lang.Object (ctrl + O 누르면 아래에 보임)
// Object클래스는 모든 클래스 상속계층도의 최상위에 있는 부모클래스이다.
// 다른 클래스로부터 상속 받지 않는  모든 클래스들은 자동적으로 Object클래스로부터 상속받게 함으로써 이것을 가능하게 한다.
// 컴파일러는 자동적으로 상속이 없는 클래스에 extends Object를 추가함으로써 모든 클래스의 조상이 되도록 한다.
// 만약 다른 클래스로부터 상속을 받는다고 하더라도 상속계층도를 따라 부모클래스, 부모클래스의 부모클래스를 찾아 올라가 보면
// 결국 최상위 조상은 Object클래스이다.

// 따라서 자바의 모든 클래스들은 Object클래스의 맴버들을 상속 받기 때문에
// Object클래스에 정의된 맴버들을 사용할 수 있다.
// toString() equals()와 같은 메서드를 따로 정의하지 않고도 사용할 수 있었던 이유이다.

package java_;

class Radio{ // extends Object가 생략되어 있다.(컴파일러가 자동으로 추가)

}

public class H_object_class {
    public static void main(String[] args) {
        Radio radio = new Radio();
        System.out.println(radio.equals(radio));
        System.out.println(radio.toString());
        System.out.println(radio.getClass());
    }
}
