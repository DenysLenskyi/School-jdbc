package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TablePopulateDao {

    public TablePopulateDao populateTableCourse(String sql) {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public TablePopulateDao populateTableGroup(String sql) {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public TablePopulateDao populateTableStudent(String sql) {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }

    public TablePopulateDao populateTableStudentCourse(String sql) {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return this;
    }
}