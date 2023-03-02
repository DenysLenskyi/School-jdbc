package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain;

import java.io.IOException;
import java.util.Properties;

public class DatabaseProperties {

    private static String dbUrl;
    private static String dbUserName;
    private static String dbPassword;

    private DatabaseProperties() {
    }

    public static void getProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(DatabaseProperties.class.getResourceAsStream("/application.properties"));
        dbUrl = (String) prop.get("db.url");
        dbUserName = (String) prop.get("db.username");
        dbPassword = (String) prop.get("db.password");
    }

    public static String getDbUrl() {
        return dbUrl;
    }

    public static String getDbUserName() {
        return dbUserName;
    }

    public static String getDbPassword() {
        return dbPassword;
    }
}
