package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionManager {

    public static Connection getConnection() {
        try {
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
            throw e;
        }
        return null;
    }
}
