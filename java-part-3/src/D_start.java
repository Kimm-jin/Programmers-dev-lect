import java.util.Scanner;

public class D_start {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 요금제 선택
        System.out.println("[1]Lite:10 [2]Basic:20 [3]Premium:30");
        D_price_plan plan = null;
        while (plan == null) {
            plan = D_price_plan.from(readInt(sc));
            if (plan == null) {
                System.out.println("1~3 중에서 선택하세요.");
            }
        }
        D_member_manager manager = new D_member_manager(plan.getCapacity());
        System.out.println("선택: " + plan + " (정원 " + plan.getCapacity() + ")");

        // 메뉴 루프
        while (true) {
            System.out.println("[수행할 업무를 선택하세요 - 현재 회원수 : " + manager.size() + "/" + manager.capacity() + "]");
            System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
            System.out.println("[4]회원전체조회 [5]회원정보 수정 [6]회원삭제");
            System.out.println("[7]프로그램 종료");
            int choice = readInt(sc);
            switch ((choice)) {
                case 1:
                    if (manager.isFull()) {
                        System.out.println("정원이 가득 찼습니다.");
                        break;
                    }
                    System.out.println("등급 [1]일반 [2]VIP");
                    int grade = readInt(sc);

                    System.out.print("이름 > ");
                    String name = sc.nextLine();
                    System.out.print("이메일 > ");
                    String email = sc.nextLine();
                    System.out.print("연락처 > ");
                    String phone = sc.nextLine();
                    if(manager.existsEmail(email)){
                        System.out.println("이미 있는 회원입니다.");
                        break;
                    }
                    D_member member = (grade==2)
                            ? new D_vip_member(name,email,phone)
                            : new D_normal_member(name,email,phone);
                    manager.add(member);
                    System.out.println("추가되었습니다.");
                    break;
                case 2:
                    System.out.println("[조회] 이메일");
                    D_member byEmail = manager.findByEmail(sc.nextLine());
                    if(byEmail==null) System.out.println("찾으시는 정보가 없습니다.");
                    else byEmail.printInfo();
                    break;
                case 3:
                    System.out.println("[조회] 아름");
                    D_member byName = manager.findByName(sc.nextLine());
                    if(byName==null) System.out.println("찾으시는 정보가 없습니다.");
                    else byName.printInfo();
                    break;
                case 4:
                    manager.printAll();
                    break;
                case 5:
                    System.out.println("[수정] 이메일");
                    String userEmail = sc.nextLine();
                    System.out.println("새 이름");
                    String newName = sc.nextLine();
                    System.out.println("새 이메일");
                    String newEmail = sc.nextLine();
                    System.out.println("새 연락처");
                    String newPhone = sc.nextLine();
                    if(manager.update(userEmail,newName,newEmail,newPhone)){
                        System.out.println("수정되었습니다.");
                    }else System.out.println("찾으시는 회원이 없습니다.");
                    break;
                case 6:
                    System.out.println("[삭제]");
                    if(manager.delete(sc.nextLine())) System.out.println("삭제하였습니다.");
                    else System.out.println("찾으시는 회원이 없습니다.");
                    break;
                case 7:
                    System.out.println("이용해주셔서 감사합니다.");
                    break;
                default:
                    System.out.println("1~7번 중에서 선택하세요.");
            }
        }
    }

    // 숫자를 안전하게 읽는다. 숫자가 아니면 -1 반환
    static int readInt(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

}
