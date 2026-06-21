package report_21_회원관리_FileIO;

import java.util.*;
import java.io.*;


public class MemberManager {
    private final String FILE = "members.txt";
    private final List<Member> members = new ArrayList<>();
    private final int capacity;

    public MemberManager(int capacity) {
        this.capacity = capacity;
        load();
    }

    public void save() {
        try (FileWriter fw = new FileWriter(FILE)) {
            for (Member member : members) {
                fw.write(member.toFileString() + "\n");
            }
        } catch (IOException e) {
            System.out.println("저장 실패" + e.getMessage());
        }
    }

    public void load() {
        File file = new File(FILE);
        if (!file.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue; // 방어코드
                String[] p = line.split(",");
                if (p.length != 4) continue; // 방어코드2
                String grade = p[0], name = p[1], email = p[2], phone = p[3];
                Member m = grade.equals("VIP")
                        ? new VipMember(name, email, phone)
                        : new NormalMember(name, email, phone);
                if (members.size() >= capacity) {
                    System.out.println("정원 초과. 더 이상 불러오지 않음");
                    return;
                }
                members.add(m);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int size() {
        return members.size();
    }

    public int capacity() {
        return capacity;
    }

    public Member findByEmail(String email) {
        for (Member m : members) {
            if (m.getEmail().equals(email)) return m;
        }
        return null;
    }

    public Member findByName(String name) {
        for (Member m : members) {
            if (m.getName().equals(name)) return m;
        }
        return null;
    }

    public void printAll() {
        if (members.isEmpty()) {
            System.out.println("등록된 회원이 없습니다.");
            return;
        }
        for (Member m : members) m.printInfo();
    }

    public void add(Member m) {
        if (members.size() >= capacity) {
            System.out.println("정원 초과");
            return;
        }
        if (findByEmail(m.getEmail()) != null) {
            System.out.println("이미 존재하는 이메일");
            return;
        }
        members.add(m);
        save();
    }

    public boolean update(String email, String name, String newEmail, String phone) {
        Member m = findByEmail(email);
        if (m == null) return false;
        if (findByEmail(newEmail) != null) {
            System.out.println("이미 존재하는 이메일");
            return false;
        }
        m.update(name, newEmail, phone);
        save();             // 수정 후 저장
        return true;
    }

    public boolean delete(String email) {
        Member m = findByEmail(email);
        if (m == null) return false;
        members.remove(m);
        save();
        return true;
    }


}
