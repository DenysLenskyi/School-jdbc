package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.io.IOException;
import java.util.Properties;

public class DatabaseProperties {

    private static String dbUrl;
    private static String dbUserName;
    private static String dbPassword;
    private static String driverName;

    private DatabaseProperties() {
    }

    public static void getProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(DatabaseProperties.class.getResourceAsStream("/application.properties"));
        dbUrl = (String) prop.get("db.url");
        dbUserName = (String) prop.get("db.username");
        dbPassword = (String) prop.get("db.password");
        driverName = (String) prop.get("driver_name");
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

    public static String getDriverName() {
        return driverName;
    }
}
