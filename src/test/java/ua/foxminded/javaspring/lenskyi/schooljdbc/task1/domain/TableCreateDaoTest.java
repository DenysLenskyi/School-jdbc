package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class TableCreateDaoTest {
    @Test
    void tablePopulateTest() {
        Properties properties = new Properties();
        FileReader reader = new FileReader();
        try {
            properties.load(TableCreateDaoTest.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driverName = properties.get("db.driver").toString();
        String url = properties.get("db.url").toString();
        String user = properties.get("db.user").toString();
        String password = properties.get("db.password").toString();
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement()) {
            statement.execute("create table if not exists test (ID INT, name TEXT)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}