package report_08_회원관리_interface;

import java.util.Scanner;
/*
    인터페이스가 유리한 경우: 상태 없이 "이런 기능을 갖춰라"는 규약만 강제할 때, 또는 여러 규약을 동시에 적용해야 할 때 (implements A, B).
 */

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[1]Lite:10 [2]Basic:20 [3]Premium:30");
        int plan = Integer.parseInt(sc.nextLine());
        MemberManager manager = new MemberManager(plan * 10);

        while (true){
            System.out.println("[수행할 업무를 선택하세요 - 현재 회원수 : " + manager.getCount() + "/" + manager.getCapacity() + "]");
            System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
            System.out.println("[4]회원전체조회 [5]회원정보 수정 [6]회원삭제");
            System.out.println("[7]프로그램 종료");
            int num = sc.nextInt();
            sc.nextLine();

            switch (num){
                case 1 : {  // 회원추가 부분 (case 1)
                    if (manager.isFull()) {
                        System.out.println("회원이 꽉 찼습니다.");
                    } else {
                        System.out.println("등급 [1]일반 [2]VIP");
                        int grade = Integer.parseInt(sc.nextLine());
                        System.out.print("이름 > ");
                        String name = sc.nextLine();
                        System.out.print("이메일 > ");
                        String email = sc.nextLine();
                        System.out.print("연락처 > ");
                        String phone = sc.nextLine();

                        if (manager.existsEmail(email)) {
                            System.out.println("이미 존재하는 회원입니다.");
                        } else {
                            Member m = (grade == 2) ? new VipMember(name, email, phone)
                                    : new NormalMember(name, email, phone);
                            manager.add(m);   // 어떤 등급이든 Member로 추가 (다형성)
                        }
//                System.out.println(manager.existsEmail(email));
                    }
                    break;
                }case 2: // 조회-email
                    System.out.print("조회할 이메일 > ");
                    String searchEmail = sc.nextLine();

                    Member emailMember = manager.findByEmail(searchEmail);

                    if (emailMember == null) {
                        System.out.println("찾는 회원이 없습니다.");
                    } else {
                        emailMember.printInfo();
                    }
                    break;

                case 3: // 조회-이름
                    System.out.print("조회할 이름 > ");
                    String searchName = sc.nextLine();

                    Member nameMember = manager.findByName(searchName);

                    if (nameMember == null) {
                        System.out.println("찾는 회원이 없습니다.");
                    } else {
                        nameMember.printInfo();
                    }
                    break;

                case 4: // 전체 조회
                    manager.printAll();
                    break;

                case 5: // 수정-email
                    System.out.print("수정할 회원 이메일 > ");
                    String targetEmail = sc.nextLine();

                    System.out.print("새 이름 > ");
                    String newName = sc.nextLine();

                    System.out.print("새 이메일 > ");
                    String newEmail = sc.nextLine();

                    System.out.print("새 연락처 > ");
                    String newPhone = sc.nextLine();

                    if (manager.update(targetEmail, newName, newEmail, newPhone)) {
                        System.out.println("회원 정보가 수정되었습니다.");
                    } else {
                        System.out.println("그런 이메일은 없습니다.");
                    }
                    break;

                case 6: // 삭제-email
                    System.out.print("삭제할 회원 이메일 > ");
                    String deleteEmail = sc.nextLine();

                    if (manager.delete(deleteEmail)) {
                        System.out.println("회원이 삭제되었습니다.");
                    } else {
                        System.out.println("그런 회원은 없습니다.");
                    }
                    break;

                case 7: // 종료
                    System.out.println("이용해주셔서 감사합니다.");
                    return;

                default:
                    System.out.println("잘못 눌렀습니다.");
                    break;
            }


        }


    }
}
