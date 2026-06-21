package report_22_회원관리_DB;

import java.sql.*;
import java.util.*;
import java.io.*;
import java.io.InputStream;
import java.util.Properties;


public class MemberManager {
    private final int capacity;

    public MemberManager(int capacity) {
        this.capacity = capacity;
    }

    public Connection connection() {

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

            return connection;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int capacity() {
        return capacity;
    }

    public void add(Member m) {
        // language=SQL
        String sql = "INSERT INTO member (grade, name, email, phone) VALUES (?, ?, ?, ?)";
        try(Connection conn = connection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, m.getGrade());
            ps.setString(2, m.getName());
            ps.setString(3, m.getEmail());
            ps.setString(4, m.getPhone());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Member toMember(ResultSet rs) throws SQLException{
        String grade = rs.getString("grade");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        return grade.equals("VIP")
                ? new VipMember(name, email, phone)
                : new NormalMember(name,email,phone);
    }

    public Member findByEmail(String email){
        // language=SQL
        String sql = "SELECT grade, name, email, phone FROM member WHERE email = ?";
        try(Connection conn = connection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()) return toMember(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public Member findByName(String name){
        // language=SQL
        String sql = "SELECT grade, name, email, phone FROM member WHERE name = ?";
        try(Connection conn = connection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()){
                if(rs.next()) return toMember(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void printAll(){
        // language=SQL
        String sql = "SELECT grade, name, email, phone FROM member";
        try(Connection conn = connection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            boolean empty = true;
            while(rs.next()){toMember(rs).printInfo(); empty = false;}
            if(empty) System.out.println("등록된 회원이 없습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean existsEmail(String email){
        // language=SQL
        String sql = "SELECT COUNT(*) FROM member WHERE email = ?";
        try(Connection conn = connection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1, email);

            try(ResultSet rs = ps.executeQuery()) {
                if(rs.next()) return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public int size(){
        // language=SQL
        String sql = "SELECT COUNT(*) FROM member";
        try(Connection conn = connection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()){
            if(rs.next())return rs.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }

    public boolean isFull(){ return size() >= capacity;}

    public boolean update(String email, String name, String newEmail, String phone){
        // language=SQL
        String sql = "UPDATE member SET name =?, email = ?, phone = ? WHERE email = ?";
        try(Connection conn = connection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,name);
            ps.setString(2,newEmail);
            ps.setString(3,phone);
            ps.setString(4,email);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(String email){
        // language=SQL
        String sql = "DELETE FROM member WHERE email = ?";
        try(Connection conn = connection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setString(1,email);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
