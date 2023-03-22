/*
package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManagerTest {
    @Test
    void connectionTest() {
        Properties properties = new Properties();
        Connection connection = null;
        try {
            properties.load(ConnectionManagerTest.class.getResourceAsStream("/application.properties"));
            String driverName = properties.get("db.driver").toString();
            String url = properties.get("db.url").toString();
            String user = properties.get("db.user").toString();
            String password = properties.get("db.password").toString();
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, user, password);
            assertEquals(true, connection != null);
        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

 */
