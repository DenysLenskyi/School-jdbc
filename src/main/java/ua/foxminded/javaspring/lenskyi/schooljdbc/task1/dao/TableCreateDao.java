package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.sql.Connection;
import java.sql.Statement;

public class TableCreateDao {

    public void createNewTables(String sql) {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}