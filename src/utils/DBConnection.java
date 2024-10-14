package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DBConnection {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "123123";
    private static final String HOST = "localhost";
    private static final String DATABASE = "logic";
    private static final String PORT = "3306";

    private static Connection connection;

    public static Connection getConnection () {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //nap driver
            connection = DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+DATABASE, USER_NAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void main(String[] args) {
        Connection a = getConnection();
        System.out.println(a);
    }
}

