package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.util.Properties;

public class DatabaseProperties {

    public static String DB_URL;
    public static String DB_USER;
    public static String DB_PASSWORD;
    public static String DB_DRIVER_NAME;

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
}