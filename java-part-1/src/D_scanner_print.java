import java.util.Scanner;

public class D_scanner_print {
    public static void main(String[] args) {
        String name = "홍길동";
        System.out.printf("이름 %s\n", name);

        int age=20;
        System.out.printf("나이 : %d\n",age);

        System.out.print("당신의 이름은? : ");
        Scanner sc = new Scanner(System.in);
        // 참조형은 주소를 참조한다.
        // 참조형 타입 생성하려면 반드시 new를 써야한다.
        // String도 참조형이긴 하나(new 써도 가능함) 좀 특이케이스
        // new를 쓰면 주소가 잡힘
        String userName = sc.nextLine();
        System.out.printf("제 이름은 %s입니다.\n",userName);
        System.out.print("당신의 나이는? : ");
        int userAge = sc.nextInt();
        System.out.printf("제 나이는 %d입니다.\n",userAge);



    }
}
