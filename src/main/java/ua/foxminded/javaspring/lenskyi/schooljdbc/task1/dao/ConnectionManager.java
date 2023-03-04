package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.DatabaseProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    public Connection getConnection() {
        try {

            Class.forName(DatabaseProperties.DB_DRIVER_NAME);
            Connection connection = DriverManager.getConnection(DatabaseProperties.DB_URL,
                    DatabaseProperties.DB_USER, DatabaseProperties.DB_PASSWORD);
            if (connection != null) {
                System.out.println("Connection Successful");
            } else {
                System.out.println("Failed to make connection!");
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            try {
                throw e;
            } catch (ClassNotFoundException ex) {
                ex.printStackTrace();
            }
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
