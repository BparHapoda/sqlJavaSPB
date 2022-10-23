package org.example;

import java.sql.SQLException;

public class ConsoleOutput implements Output{
    public void print (String query){
        try {
            System.out.println(DataBaseQuery.selectQuery(query));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
