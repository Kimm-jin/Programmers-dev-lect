import java.util.*;

import static java.lang.System.exit;
/*
날짜 입력 (예: 2024-09-04) > 2024-09-04
항목 이름 > 공책
금액 > 1000
더 추가할까요? (y/n) > y
항목 이름 > 연필
금액 > 300
더 추가할까요? (y/n) > n

[2024-09-04] 등록 완료
공책 : 1000원
연필 : 300원
합계 : 1300원
 */

/*
날짜를 입력받는다.
그 날짜에 쓸 빈 List<Item>을 만든다.
반복하면서 항목 이름·금액을 입력받아 Item을 만들고 List에 add한다.
"더 추가?"가 n이면 반복 종료.
data.put(날짜, 리스트)로 저장한다.
항목들과 합계를 출력한다.

 */

// Map(날짜, list(이름,가격))
// AccountBook 을 구현한다. 실제 로직과 데이터 (Map)을 가진다.
public class AccountBookImpl implements AccountBook {
    private Map<String, List<Item>> data = new HashMap<>();
    private Scanner sc = new Scanner(System.in);

    public void addAccount() {
        System.out.printf("날짜 입력 (예 : 2024-09-04) > ");
        String date = sc.nextLine();
        List<Item> listItem;
        if (data.containsKey(date)) {
            listItem = data.get(date); // 해당 날짜에 저장된 값 꺼냄
        } else {// 최초로 등록
            listItem = new ArrayList<>();
            data.put(date, listItem);
        }

        while (true) {
            System.out.printf("항목 이름 > ");
            String name = sc.nextLine();

            System.out.printf("금액 > ");
            int price = Integer.parseInt(sc.nextLine());

            listItem.add(new Item(name, price)); // List(name, price),


            System.out.println("더 추가할까요? (y/n) >");
            String check = sc.nextLine();
            if (check.equals("n")) {
                int sum = 0;
                System.out.printf("[%s] 등록 완료\n", date);
                for (Item item : listItem) {
                    sum += item.getPrice();
                    System.out.println(item.getName() + " : " + item.getPrice() + " 원");
                }
                System.out.println("합계 : " + sum);
                break;
            } else if (check.equals("y")) continue;
            else {
                System.out.println("잘못 입력하셧습니다. 종료합니다");
                exit(0);
            }

        }


    }

    public void showAccount() {
        if (data.isEmpty()) {
            System.out.println("기록이 없습니다.");
            return;
        } else {
            for (String Key : data.keySet()) {
                System.out.println(Key);
            }
        }
        System.out.printf("날짜 입력 (예 : 2024-09-04) > ");
        String date = sc.nextLine();
        List<Item> itemList = data.get(date);

        //
        if (data.containsKey(date)) {
            int sum = 0;
            for (Item item : itemList) {
                System.out.println(item.getName() + " : " + item.getPrice());
                sum += item.getPrice();
            }
            System.out.println(sum);
        } else {
            System.out.println("그런 날짜 없습니다.");
        }

    }

    public void deleteAll() {
        if (!data.isEmpty()) {
            for (String str : data.keySet()) {
                System.out.println(str);
            }
        } else {
            System.out.println("기록이 없습니다");
            return;
        }
        System.out.printf("삭제할 날짜를 입력해주세요 : ");
        String date = sc.nextLine();

        if (data.containsKey(date)) {
            data.remove((date));
            System.out.println(date + " 날짜가 삭제되었습니다.");
            return;
        } else {
            System.out.println("없는 날짜입니다.");
            return;
        }
    }

    public void deleteItem() {
        if(data.isEmpty()){
            System.out.println("저장된 날짜가 없습니다.");
            return;
        }
        for(String str : data.keySet()){
            System.out.println(str);
        }
        System.out.println("날짜를 입력하세요 : ");
        String date = sc.nextLine();
        List<Item> itemList = data.get(date);
        int i=1;
        for (Item item : itemList) {
            System.out.println(i++ + ". "+item.getName() + " : " + item.getPrice());
        }
        System.out.printf("삭제할 번호를 입력하세요 : ");
        int  index = Integer.parseInt(sc.nextLine());
        if(index>itemList.size() || index<1){
            System.out.println("잘못 입력하셨습니다.");
            return;
        }
        itemList.remove(index-1);
        if(itemList.isEmpty()){
            data.remove(date);
        }
    }
}
