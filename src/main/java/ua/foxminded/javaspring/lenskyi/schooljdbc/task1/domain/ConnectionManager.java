package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    public Connection getConnection() {
        String Url = DatabaseProperties.getDbUrl();
        String User = DatabaseProperties.getDbUserName();
        String Password = DatabaseProperties.getDbPassword();
        try {
            Connection connection = DriverManager.getConnection(Url, User, Password);
            if (connection != null) {
                System.out.println("Connection Successful");
            } else {
                System.out.println("Failed to make connection!");
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception in close connection method");
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception in close statement method");
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("SQL Exception in close resultSet method");
            }
        }
    }
}
