/*
package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Properties;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabasePropertiesTest {
    @Test
    void propertiesTest() {
        Properties properties = new Properties();
        try {
            properties.load(DatabasePropertiesTest.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
           e.printStackTrace();
        }
        String driverName = properties.get("db.driver").toString();
        String url = properties.get("db.url").toString();
        String user = properties.get("db.user").toString();
        String password = properties.get("db.password").toString();
        assertEquals("org.h2.Driver", driverName);
        assertEquals("jdbc:h2:mem:testdb", url);
        assertEquals("test", user);
        assertEquals("test", password);
    }
}

db.driver=org.h2.Driver
db.url=jdbc:h2:mem:testdb
db.user=test
db.password=test

 */