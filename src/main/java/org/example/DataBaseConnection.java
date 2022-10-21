package org.example;

import java.sql.*;

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
    public static void selectQuery(String query) throws SQLException {
        Connection connection = DataBaseConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String str ;
header();
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                System.out.printf("|%-10d|%-20s|%-20s|%-20s|%3s|%12s|",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("sursurname"),
                        rs.getString("surname"),
                        " "+rs.getString("pol")+" ",
                        " "+rs.getString("birthday")+" "
                );
                System.out.print("\n");



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        footer();

        rs.close();
        connection.close();

    }


    public static void header (){
        footer();
        System.out.printf("|%-10s|%-20s|%-20s|%-20s|%3s|%12s|",
                "   Номер",
                "         Имя",
                "      Отчество",
                "     Фамилия",
                "Пол",
                "Дата рожд"
        );
        System.out.print("\n");
        footer();

    }
    public static void footer (){
        System.out.println("--------------------------------------------------------------------------------------------");
    }

}
