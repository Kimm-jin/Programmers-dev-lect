/*
연산자
    산술 연산자 : +, -, *, /, %
    단항 연산자 : ++, --;
    비교 연산자 : >, <, >=, <=, ==, !=
    대입 연산자 : =
    기타 : ?:, 복합대입연산자

 연산자 우선순위
    곱셈과 나눗셈은 덧샘과 뺼셈보다 우선순위가 높다.

 */
public class F_operator {
    // [산술 연산자]
    static void operExam1(){
        int a= 10;
        int b= 4;
        System.out.printf("%d + %d = %d\n",a,b, a+b);
        System.out.printf("%d - %d = %d\n",a,b, a-b);
        System.out.printf("%d * %d = %d\n",a,b, a*b);
        System.out.printf("%d / %d = %d\n",a,b, a/b);
        // System.out.printf("%d %% %d = %d\n",a,b, (float)a%b);
        // 타입에 맞게 %f
        System.out.printf("%d %% %d = %f\n",a,b, (float)a%b);
        // %연산자를 출력하기 위해선 %%를 사용해야한다.
        // 단일 %는 포맷 지정자로 인식이 안되기 때문
    }

    static void operExam2(){
        // 증감 연산자 : 피연산자의 값을 1 증가시킨다.
        int i=5;
        System.out.println("======== 증감 ========");
        System.out.println("전위형 :" + ++i);
        System.out.println(i);
        System.out.println("후위형 :" + i++);
        System.out.println(i);
        i=i+1;
        i+=1;
        System.out.println("i =" + i);
        // 감소 연산자 : 피연산자의 값을 1 감소시킨다.
        i = 5;
        System.out.println("========= 감소 =========");
        System.out.println("전위형 : " + --i);
        System.out.println(i);
        System.out.println("후위형 : " + 4);
        System.out.println(i);
        i = i - 1;
        i -= 1;
        System.out.println("i = " + i);
    }

    // [비교 연산자]
    public static void operExam3() {
        System.out.printf("10 == 10.0f \t %b\n", 10 == 10.0f );
        System.out.printf("'0' == 0 \t %b\n", '0' == 0);
        System.out.printf("'A' == 65 \t %b\n", 'A' == 65 );
        System.out.printf("'A' > 'B' \t %b\n", 'A' > 'B');
        System.out.printf("'A' + 1 != 'B' \t %b\n", 'A' + 1 != 'B');
    }

    // [문자열 비교]
    static void operExam4(){
        String str1 = new String("Hello");
        String str2 = new String("Hello");
        System.out.println(str1 == str2);
        // String은 참조연산자이고 new는 새로운 heap에 할당하는것이다.
        // 그렇기 때문에 str1과 str2의 주소값은 서로 다르기 때문에 false가 출력된다.

        String str3 = "Hello";
        String str4 = "Hello";
        System.out.println(str3 == str4);
        // new를 쓰지 않아서 jdk?가 메모리 공간을 절약하기 위해 같은 주소값을 할당한다.
        // 그렇기 때문에 str3과 str4의 주소값이 같아 true가 출력된다.

        System.out.println(str1.equals(str2));
        // 자바는 문자열 비교를 위해 '=='이 아닌 equals() 를 쓴다.
    }

    // [논리 연산자]
    // && : 그리고(AND) - 모두 true일 때, true
    // || : 또는(OR) - 둘 중 하나만 true여도 true
    // ! : 논리 부정 연산 - x가 true 일 때, !x는 false
    public static void operExam5() {

        boolean a = true;
        boolean b = false;
        boolean c = true;

        System.out.println("a && b : " + (a && b));
        System.out.println("c && a : " + (c && a));
        System.out.println("a || b : " + (a || b));
        System.out.println("!a : " + !a);
    }
    // 비트연산자, 쉬프트연산자


    public static void main(String[] args) {
        operExam1();
        operExam2();
        operExam3();
        operExam4();
        operExam5();
    }
}
