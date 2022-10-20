package org.example;

import java.sql.*;
import java.util.Calendar;


public class Main {


    public static void main(String[] args) throws SQLException {
        String query = "SELECT * FROM customers";


        insert("Павел", "Cтепанов", "Борисович", "М", 12, 11, 1985);
        selectQuery(query);
    }

    public static void selectQuery(String query) throws SQLException {
        Connection connection = null;
        connection = DataBaseConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String str = null;
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                str = rs.getString("id") + rs.getString("name") + rs.getString("surname");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            System.out.println(str);

        }

        rs.close();
        connection.close();

    }

    public static void insert(String name, String surname, String sursurname,
                              String pol, int day, int month, int year) throws SQLException {
        Connection connection = null;
        java.sql.Date sqlDate = new java.sql.Date(year-month-day);
        connection = DataBaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement
                ("INSERT INTO customers (name,surname,sursurname,pol,birthday) VALUES (?, ?, ?, ?,?)");
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, surname);
        preparedStatement.setString(3, sursurname);
        preparedStatement.setString(4, pol);
        preparedStatement.setObject(5,sqlDate );
        preparedStatement.executeUpdate();
        preparedStatement.close();


    }
}
