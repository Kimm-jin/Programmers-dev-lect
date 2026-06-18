package report_20_가계부2_FileIO;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AccountBookImpl implements AccountBook {
    private final String DIR = "accountbook";
    private Scanner sc = new Scanner(System.in);

    public AccountBookImpl() {
        File folder = new File(DIR);
        if (!folder.exists()) folder.mkdir();
    }

    @Override
    public void addAccount() {
//        오늘 날짜로 파일 경로를 만든다. (예: accountbook/2024-09-04.txt)
//        항목 이름·금액을 반복 입력받아 임시로 모으고 합계를 계산한다.
//                append 모드(new FileWriter(file, true))로 파일에 형식대로 쓴다.
//        파일이 없으면 FileWriter가 알아서 만들어 준다. (별도 createNewFile 불필요)
//        저장한 내용을 화면에도 출력한다.
        String today = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        File file = new File(DIR, today + ".txt");
        int total = 0;
        int price = 0;
        StringBuilder sb = new StringBuilder();

        System.out.print("이름 : ");
        String name = sc.nextLine();

        System.out.print("금액 : ");
        try {
            price = Integer.parseInt(sc.nextLine());
            total += price;
        } catch (NumberFormatException e) {
            System.out.println("숫자로 입력하세요");
        }



        if (file.exists()) { // 기존 파일이 있는지
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) { // 한줄씩 -> 마지막줄까지
                    if (line.startsWith("합계")) { // 합계로 시작하는 줄
                        // target -> replacement
                        String num = line.replace("합계 : ", "").replace("원", "");
                        total += Integer.parseInt(num);
                    } else sb.append(line + "\n");
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        sb.append("이름 : " + name + "\n" + "금액 : " + price + "원\n");
        sb.append("합계 : " + total + "원\n");

        try (FileWriter fw = new FileWriter(file)) {
            fw.write(sb.toString());
        } catch (IOException e) {
            System.out.println("저장 중 오류 : " + e.getMessage());
        }
    }

    @Override
    public void showAccount() {
        File folder = new File(DIR);
        String[] files = folder.list();

        for (String fileName : files) {
            if (fileName.endsWith(".txt"))
                System.out.println(fileName.replace(".txt", ""));
        }

        System.out.println("보고싶은 날짜를 입력");
        File file = new File(DIR, sc.nextLine() + ".txt");
        if (file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.out.println("파일을 읽는 중 오류가 발생했습니다."+e.getMessage());
            }
        }else{
            System.out.println("해당 날짜는 없습니다.");
        }
    }


    @Override
    public void deleteAccount() {
        File folder = new File(DIR);
        String[] files = folder.list();


        for (String fileName : files) {
            if (fileName.endsWith(".txt"))
                System.out.println(fileName.replace(".txt", ""));
        }

        System.out.println("삭제할 날짜를 입력하세요");
        File file = new File(DIR,sc.nextLine()+".txt");
        if(file.exists()){
            if(file.delete()) System.out.println("삭제하였습니다.");
            else System.out.println("삭제에 실패했습니다.");
        }else{
            System.out.println("해당 날짜는 없습니다.");
        }
    }
}

