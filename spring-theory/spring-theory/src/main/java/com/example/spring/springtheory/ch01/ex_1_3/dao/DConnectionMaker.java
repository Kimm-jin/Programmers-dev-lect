package com.example.spring.springtheory.ch01.ex_1_3.dao;

import com.example.spring.springtheory.ch01.ex_1_2.dao.SimpleConnectionMaker_2;

import java.sql.Connection;
import java.sql.SQLException;

public class DConnectionMaker implements SimpleConnectionMaker_2 {
    @Override
    public Connection makeNewConnection() throws ClassNotFoundException, SQLException {
        return null;
    }
}
