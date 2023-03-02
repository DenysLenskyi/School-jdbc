package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class ConnectionManager {

    private String dbConnUrl;
    private String dbUserName;
    private String dbPassword;

    public Connection getConnection() {

        try {
            getProperties();
            Connection connection = DriverManager.getConnection(dbConnUrl, dbUserName, dbPassword);
            if (connection != null) {
                System.out.println("Connection Successful");
            } else {
                System.out.println("Failed to make connection!");
            }
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw e;
        }
        return null;
    }

    private void getProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(getClass().getResourceAsStream("/application.properties"));
        dbConnUrl = (String) prop.get("db.url");
        dbUserName = (String) prop.get("db.username");
        dbPassword = (String) prop.get("db.password");
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
