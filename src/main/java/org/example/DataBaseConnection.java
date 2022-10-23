package org.example;

import java.sql.*;

import static java.lang.String.format;

public class DataBaseConnection {
    static final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/customers";
    static final String USER = "postgres";
    static final String PASS = "12345";

    public static Connection getConnection () {

        try {
            return DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
