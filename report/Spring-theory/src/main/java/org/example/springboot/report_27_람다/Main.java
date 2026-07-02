package org.example.springboot.report_27_람다;

import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    static void main() {

        System.out.println("==== 1. 익명 클래스 vs 람다 (같은 동작) ====");
        /*Operation add = new Operation() {
            @Override
            public int apply(int a, int b) {
                return a+b;
            }
        };*/

        Operation add = (a,b) -> a+b;
        System.out.println(add.apply(3,4));

        System.out.println("==== 2. 람다로 만든 연산들 ====");

        Operation add2 = (a,b) ->{return a+b;};
        Operation add3 = (int a, int b) -> {return a+b;};
        Operation sub = (a,b)->a-b;
        Operation mul = (a,b)->a*b;
        Operation div = (a,b)->a/b;

        System.out.println(add2.apply(3,4));
        System.out.println(add3.apply(3,4));
        System.out.println(sub.apply(3,4));
        System.out.println(mul.apply(3,4));
//        ArithmeticException
//        System.out.println(div.apply(3,0));



        System.out.println("==== 3. 매개변수 개수별 람다 ====");

        Runnable hello = () -> System.out.println("[0] hello");
        hello.run();

        Printer log = msg -> System.out.println("[1] log : "+msg);
        log.print("시작");

        Operation add4 = (a,b)->a+b;
        System.out.println("[2] 5 + 6 = "+add4.apply(5,6));


        System.out.println("==== 4. 실전 : Comparator ====");

        ArrayList<String > list = new ArrayList<>();
        list.add("가나다");
        list.add("가");
        list.add("라마");
        System.out.println(list);

//        list.sort((a, b) -> a.length() - b.length());
        list.sort(String::compareTo);
        System.out.println(list);

//        list.sort((a,b)->b.length()-a.length());
        list.sort(Comparator.comparingInt(String::length));
        System.out.println(list);

        list.sort((a,b)->a.compareTo(b));
        System.out.println(list);


    }
}
