package report_21_회원관리_FileIO;


import java.util.Scanner;

public class Main {
    static int readInt(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("[1]Lite:10 [2]Basic:20 [3]Premium:30");
        PricePlan plan = null;
        while (plan == null) {
            int choice = readInt(sc);
            plan = PricePlan.from(choice);
            if (plan == null) System.out.println("1 ~ 3중에서 입력하세요.");
        }
        MemberManager manager = new MemberManager(plan.getCapacity());
        while (true) {
            System.out.println("[수행할 업무를 선택하세요 - 현재 회원수 : " + manager.size() + "/" + manager.capacity() + "]");
            System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
            System.out.println("[4]회원전체조회 [5]회원정보 수정 [6]회원삭제");
            System.out.println("[7]프로그램 종료");
            int menu = readInt(sc);
            switch (menu) {
                case 1:
                    System.out.println("등급 [1]일반 [2]VIP");

                    int grade = readInt(sc);
                    if (grade > 2 || grade < 1) {
                        System.out.println("1과 2중에서 고르세요.");
                        continue;
                    }
                    System.out.print("이름 > ");
                    String name = sc.nextLine();
                    System.out.print("이메일 > ");
                    String email = sc.nextLine();
                    System.out.print("연락처 > ");
                    String phone = sc.nextLine();

                    Member m = (grade == 2) ? new VipMember(name, email, phone)
                            : new NormalMember(name, email, phone);
                    manager.add(m);
                    break;
                case 2:
                    System.out.print("조회할 이메일 > ");
                    String searchEmail = sc.nextLine();

                    Member emailMember = manager.findByEmail(searchEmail);

                    if (emailMember == null) {
                        System.out.println("찾는 회원이 없습니다.");
                    } else {
                        emailMember.printInfo();
                    }
                    break;
                case 3:
                    System.out.print("조회할 이름 > ");
                    String searchName = sc.nextLine();
                    Member nameMember = manager.findByName(searchName);

                    if (nameMember == null) {
                        System.out.println("찾는 회원이 없습니다.");
                    } else {
                        nameMember.printInfo();
                    }
                    break;
                case 4:
                    manager.printAll();
                    break;
                case 5:
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
                case 6:
                    System.out.print("삭제할 회원 이메일 > ");
                    String deleteEmail = sc.nextLine();

                    if (manager.delete(deleteEmail)) {
                        System.out.println("회원이 삭제되었습니다.");
                    } else {
                        System.out.println("그런 회원은 없습니다.");
                    }
                    break;
                case 7:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;
                default:
                    System.out.println("잘못 눌렀습니다.");
                    break;
            }
        }
    }
}
