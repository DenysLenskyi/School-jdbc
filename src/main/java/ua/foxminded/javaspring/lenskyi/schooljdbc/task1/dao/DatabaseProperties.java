package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.util.Properties;

public class DatabaseProperties {

    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;
    private static String DB_DRIVER_NAME;

    static {
        try {
            Properties prop = new Properties();
            prop.load(DatabaseProperties.class.getResourceAsStream("/application.properties"));
            DB_URL = prop.getProperty("db.url");
            DB_USER = prop.getProperty("db.username");
            DB_PASSWORD = prop.getProperty("db.password");
            DB_DRIVER_NAME = prop.getProperty("db.driver");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    private DatabaseProperties() {
    }

    public static String getDbUrl() {
        return DB_URL;
    }

    public static String getDbUser() {
        return DB_USER;
    }

    public static String getDbPassword() {
        return DB_PASSWORD;
    }

    public static String getDbDriverName() {
        return DB_DRIVER_NAME;
    }
}