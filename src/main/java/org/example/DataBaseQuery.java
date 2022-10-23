package org.example;

import java.sql.*;

public class DataBaseQuery {

    public static void insert(String name, String surname, String sursurname,
                              String pol, String date) throws SQLException {
        Connection connection = null;
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
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
        connection.close();

    }
    public static String header (){
        return   footer()+ String.format("|%-10s|%-20s|%-20s|%-20s|%3s|%12s|",
                "   Номер",
                "     Фамилия",
                "         Имя",
                "      Отчество",
                "Пол",
                "Дата рожд") +"\n"+ footer();

    }
    public static String footer (){
        return "--------------------------------------------------------------------------------------------";
    }

    public static String selectQuery(String query) throws SQLException {
        Connection connection = DataBaseConnection.getConnection();
        ResultSet rs = null;
        try {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String str = header();
        while (true) {
            try {
                if (!rs.next()) break;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                str+= String.format("|%-10d|%-20s|%-20s|%-20s|%3s|%12s|",
                        rs.getInt("id"),
                        rs.getString("surname"),
                        rs.getString("name"),
                        rs.getString("sursurname"),
                        " "+rs.getString("pol")+" ",
                        " "+rs.getString("birthday")+" "
                );
                str+="\n";



            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        str+=footer();

        rs.close();
        connection.close();
return str;
    }


}
