package report_20_가계부2_FileIO;

import java.util.Scanner;

public class Start {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AccountBook book = new AccountBookImpl();
        while (true) {
            System.out.println("===== 가계부 (File) =====");
            // 1~4 메뉴 출력
            System.out.println("1. 내역 추가");
            System.out.println("2. 내역 조회");
            System.out.println("3. 내역 삭제");
            System.out.println("4. 종료");
            int menu = Integer.parseInt(sc.nextLine());

            switch (menu) {
                case 1: book.addAccount(); break; // 나중에
                case 2: book.showAccount(); break;
                case 3: book.deleteAccount();break;
                case 4: System.out.println("종료합니다"); return;
                default: System.out.println("잘못된 번호입니다");
            }
        }
    }
}
