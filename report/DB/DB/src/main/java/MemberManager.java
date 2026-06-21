import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.io.*;
import java.io.InputStream;
import java.util.Properties;


public class MemberManager {
    public Connection connection(){
        Properties props = new Properties();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("db.properties")) {
            if (is == null) {
                throw new RuntimeException("db.properties 파일을 찾을 수 없습니다.");
            }

            props.load(is);

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Conn Success");

            return connection;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private final int capacity;

    public MemberManager(int capacity) {
        this.capacity = capacity;
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
