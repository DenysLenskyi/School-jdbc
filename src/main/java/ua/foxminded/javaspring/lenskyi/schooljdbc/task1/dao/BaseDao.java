package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.sql.Connection;
import java.sql.Statement;

public class BaseDao {

    private static BaseDao baseDao = new BaseDao();

    public BaseDao() {
    }

    public static BaseDao getBaseDao() {
        return baseDao;
    }

    public void executeScript(String sql) {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}