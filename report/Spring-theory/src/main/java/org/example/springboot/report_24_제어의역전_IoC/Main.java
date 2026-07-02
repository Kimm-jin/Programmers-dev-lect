package org.example.springboot.report_24_제어의역전_IoC;


import java.util.Scanner;

public class Main {
    static void main() {




//        CoffeeMaker c1 = new CoffeeMaker(new ColombiaBean());
//        CoffeeMaker c2 = new CoffeeMaker(new EthiopiaBean());
//        c1.print();
//        c2.print();
//        System.out.println();

//        CoffeeMaker mk = new CoffeeContainer().getCoffeeMaker();
        System.out.println("1, ColombiaBean / 2, EthiopiaBean");
        Scanner sc = new Scanner(System.in);
        String st = sc.nextLine();
        CoffeeMaker mk = new CoffeeContainer().getCoffeeMaker(st);
        mk.print();
        System.out.println();

        Hollywood hollywood = new Hollywood();
        Hollywood.Button button = hollywood.new Button();
        Hollywood.LikeAction1 Action1 = hollywood.new LikeAction1();
        Hollywood.LikeAction2 Action2 = hollywood.new LikeAction2();
        Hollywood.LikeAction3 Action3 = hollywood.new LikeAction3();

        button.setListener(Action1);
        button.setListener(Action2);
        button.setListener(Action3);
        button.press();



    }
}
