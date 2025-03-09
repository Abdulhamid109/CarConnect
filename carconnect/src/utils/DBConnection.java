package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/Carconnect";
    private static final String USER = "root";
    private static final String PASSWORD = "Learn@123";
    public static Connection getconnection(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL,USER,PASSWORD);
            System.out.println("Successfully connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
