package com.example.spring.springtheory.ch01.ex_1_3.dao;

import com.example.spring.springtheory.ch01.ex_1_1.domain.User;
import com.example.spring.springtheory.ch01.ex_1_2.dao.DConnectionMaker_2;
import com.example.spring.springtheory.ch01.ex_1_2.dao.SimpleConnectionMaker_2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// * 문제점


public abstract class UserDAO {

    private SimpleConnectionMaker_2 simpleConnectionMaker;
    // 다형성
    public UserDAO() {
        simpleConnectionMaker = new DConnectionMaker_2();
    }
    public void add(User user) throws ClassNotFoundException, SQLException {

        String query = "INSERT INTO users (id, name, password) VALUES (?, ?, ?)";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            pstmt.setString(1, user.getId());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getPassword());
            pstmt.executeUpdate();
        }

    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM users WHERE id = ?";

        try (
                Connection conn = getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);
        ) {
            pstmt.setString(1, id);
            ResultSet resultSet = pstmt.executeQuery();

            resultSet.next();

            User user = new User();
            user.setId( resultSet.getString("id") );
            user.setName( resultSet.getString("name") );
            user.setPassword( resultSet.getString("password") );

            return user;
        }

    }

    // UserDAO의 소스코드를 제공하면, getConnection() 메서드를 원하는 방식으로 확장한후
    // UserDAO의 기능과 함께 사용할 수 있다.
    // 기존에는 같은 클래스에 다른 메서드로 분리됐던 DB 커넥션 연결이라는 관심을
    // 이번에는 상속을 통해 서브클래스로 분리해버리는 것이다.
    public abstract Connection getConnection() throws ClassNotFoundException, SQLException;

}