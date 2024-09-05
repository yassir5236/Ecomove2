
/*
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/ecomove";
    private static final String USER = "postgres";
    private static final String PASSWORD = "User_Password";

    private DatabaseConnection() {
        // Constructeur privé pour empêcher l'instanciation
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
*/





package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:postgresql://localhost:5432/ecomove";
    private static final String USER = "postgres";
    private static final String PASSWORD = "User_Password";

    private static Connection connection;

    private DatabaseConnection() {
    }

    public static synchronized Connection getConnection() {
        if (connection == null) {
            try {
                System.out.println("here before initializing connection");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
        return connection;
    }

    public static synchronized void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }
}
