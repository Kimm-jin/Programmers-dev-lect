package report_06_반려동물키우기;

import java.util.Scanner;

public class start {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.printf("반려동물의 이름을 지어주세요 : ");
        String name = sc.nextLine();
        PetInfo pet = new PetInfo(name);
        PetImpl myPet = new PetImpl(pet);

        while (true) {
            System.out.println("\n무엇을 할까요? [1]먹이주기 [2]놀아주기 [3]상태보기 [4]종료");
            System.out.print("> ");
            int menu = Integer.parseInt(sc.nextLine());  // nextLine 으로 받아 숫자 변환(입력 꼬임 방지)

            if (menu == 1)      { myPet.feed();  myPet.showStatus(); }
            else if (menu == 2) { myPet.play();  myPet.showStatus(); }
            else if (menu == 3) { myPet.showStatus(); }
            else if (menu == 4) { System.out.println("안녕!"); break; }
            else                { System.out.println("1~4 중에 골라주세요."); }
        }
    }
}
