package report_04_회원관리;

import java.util.Scanner;


public class member_management {
    static int totalCnt = 0;   // 정원 (요금제로 결정)
    static int memberCnt = 0;  // 현재 회원 수 (= 다음에 채울 칸의 인덱스)

    // 요금제를 사용자한테 받는 함수를 완성해주세요.
    // [1]Lite : 10명 [2]Basic : 20명 [3]Premium : 30명
    public static int printPricePlan() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[요금제를 선택하세요]");
        System.out.println("[1]Lite : 10명 [2]Basic : 20명 [3]Premium : 30명");
        return sc.nextInt();
    }

    public static int printMenu() {
        Scanner sc = new Scanner(System.in);
        System.out.println("[수행할 업무를 선택하세요 - 현재 회원수 : " + memberCnt + "/" + totalCnt + "]");
        System.out.println("[1]회원추가 [2]회원조회(메일) [3]회원조회(이름)");
        System.out.println("[4]회원전체조회 [5]회원정보 수정 [6]회원삭제");
        System.out.println("[7]프로그램 종료");
        return sc.nextInt();
    }

    // 1. 회원추가
    public static void addMember(String members[][]){
        //1-1 회원 꽉 찼을 때 -> 추가x '회원이 꽉 찼습니다' 안내 후 종료
        if(memberCnt==totalCnt){
            System.out.println("회원이 꽉 찼습니다.");
            return;
        }

        Scanner sc = new Scanner(System.in);
        System.out.println("이름을 입력하세요.");
        String name = sc.nextLine();
        System.out.println("이메일을 입력하세요.");
        String email = sc.nextLine();
        System.out.println("연락처를 입력하세요.");
        String phone = sc.nextLine();

        members[memberCnt][0] = null;
        members[memberCnt][1] = null;
        members[memberCnt][2] = null;
        if ( checkEmail(members, email) ) {
            System.out.println("이미 존재하는 회원입니다.");
            return;
        }
        // 저장
        members[memberCnt][0] = name;
        members[memberCnt][1] = email;
        members[memberCnt][2] = phone;

        memberCnt++;
    }



    //1-2 checkEmail -> 이메일 중복 받지 않음, '이미 존재하는 회원입니다' 안내 후 종료
    public static boolean checkEmail(String members[][], String email){
        // 1. 이름, 2. 이메일, 3. 폰번호
        // 값이 없으면 members에서 nullpointException 생길 수 있음.
        // equals 메서드는 null예외처리가 되어있다. 그렇기때문에 앞에 비교값에 무조건 값이 있는것을 사용해야한다.
        // member[i][1]에 값을 넣지 않았따면 메모리 할당 직후엔 null상태이다.
        for (int i = 0; i <members.length ; i++) {
            if(email.equals(members[i][1])) return true;
        }return false;
    }

    // 2. 회원조회(메일)
    public static void selectEmail(String[][] members) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[조회] 이메일을 입력하세요.");
        String email = sc.nextLine();

        for (int i = 0; i < members.length; i++) {
            if ( email.equals(members[i][1]) ) {
                System.out.println("[이름] " + members[i][0] + ", [메일] " + members[i][1] + ", [연락처] " + members[i][2]);
                return;
            }
        }

        System.out.println("찾으시는 정보가 없습니다.");
    }

    // 3. 회원조회(이름) -> selectName
    public static void selectName(String[][] members) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[조회] 이름을 입력하세요.");
        String name = sc.nextLine();

        for (int i = 0; i < members.length; i++) {
            if ( name.equals(members[i][0]) ) {
                System.out.println("[이름] " + members[i][0] + ", [메일] " + members[i][1] + ", [연락처] " + members[i][2]);
                return;
            }
        }

        System.out.println("찾으시는 정보가 없습니다.");
    }


    // 4. 회원전체조회
    public static void selectAll(String[][] members) {
        for (int i = 0; i < members.length; i++) {
            System.out.println("[이름] " + members[i][0] + ", [메일] " + members[i][1] + ", [연락처] " + members[i][2]);
        }
    }
    // 5. 회원정보 수정
    // -> 이메일을 입력 -> 일치 시 수정 이름, 이메일, 연락처 모두 수정
    public static void updateMember(String[][] members){
        Scanner sc = new Scanner(System.in);
        System.out.printf("이메일을 입력하세요 : ");
        String email = sc.nextLine();
        int idx = -1;
        for (int i = 0; i <members.length ; i++) {
            if(email.equals(members[i][1])){
                System.out.printf("수정 할 이름을 입력하세요 : ");
                members[i][0] = sc.nextLine();
                System.out.printf("수정 할 Email을 입력하세요 : ");
                members[i][1] = sc.nextLine();
                System.out.printf("수정 할 연락처을 입력하세요 : ");
                members[i][2] = sc.nextLine();
            }
        }
        if(idx==-1){
            System.out.println("그런 이메일은 없습니다.");
            return;
        }
    }

    // 6. 회원 삭제 + 인덱스 번호 당기기
    public static void deleteMember(String members[][]){
        int idx=-1;
        String email;
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 이메일을 입력하세요 : ");
        email = sc.nextLine();
        // 있는지 확인
        for (int i = 0; i < members.length; i++) {
            if(email.equals(members[i][1])){
                idx=i;
                // 뒤에 값이 있다면 덮어씌우기
                if(members[i+1]!=null){ // 뒤에 값이 있다면
                    members[i][0]=members[i+1][0];
                    members[i][1]=members[i+1][1];
                    members[i][2]=members[i+1][2];

                    members[i+1][0] = null;
                    members[i+1][1] = null;
                    members[i+1][2] = null;
                }else{ //뒤에 값이 없다면
                    members[i][0] = null;
                    members[i][1] = null;
                    members[i][2] = null;
                }
            }
        }
        if(idx==-1){
            System.out.println("그런 회원은 없습니다.");
            return;
        }
    }




    public static void main(String[] args) {
        // printPricePlan 실행 -> 2차원 저장공간이 생성되어야 한다.
        // 이름, 이메일, 연락처 를 받는다.
        int num = printPricePlan();
        totalCnt = num * 10;
        String members[][] = new String[num * 10][3];

        // printMenu를 구성
        // loop -> 7번 , 메뉴값 잘못눌렀을 경우 사용자한테 안내멘트


        while ( true ) {
            int menu = printMenu();
            System.out.println(menu);
            switch (menu) {
                case 1:
                    addMember(members);
                    break;
                case 2:
                    selectEmail(members);
                    break;
                case 3:
                    selectName(members);
                    break;
                case 4:
                    selectAll(members);
                    break;
                case 5:
                    updateMember(members);
                    break;
                case 6:
                    deleteMember(members);
                    break;
                case 7:
                    System.out.println("이용해주셔서 감사합니다.");
                    return;
                default:
                    System.out.println("잘 못 눌렀습니다.");
                    break;
            }
        }
    }
}
