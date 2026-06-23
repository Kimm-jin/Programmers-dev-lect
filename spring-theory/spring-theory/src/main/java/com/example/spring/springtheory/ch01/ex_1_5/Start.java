package com.example.spring.springtheory.ch01.ex_1_5;
import com.example.spring.springtheory.ch01.ex_1_4.dao.DaoFactory;
import com.example.spring.springtheory.ch01.ex_1_4.dao.UserDAO;

// * 스프링의 제어의 역전(IoC, Inversion of Control)


public class Start {
    static void main(String[] args) {
        UserDAO dao = new DaoFactory().userDAO();
    }
}