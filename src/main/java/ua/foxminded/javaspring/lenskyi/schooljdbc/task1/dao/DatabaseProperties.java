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
            DB_URL = prop.get("db.url").toString();
            DB_USER = prop.get("db.username").toString();
            DB_PASSWORD = prop.get("db.password").toString();
            DB_DRIVER_NAME = prop.get("db.driver").toString();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
}