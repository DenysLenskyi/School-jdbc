package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    private ConnectionManager() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DatabaseProperties.DB_URL,
                DatabaseProperties.DB_USER, DatabaseProperties.DB_PASSWORD);
    }
}