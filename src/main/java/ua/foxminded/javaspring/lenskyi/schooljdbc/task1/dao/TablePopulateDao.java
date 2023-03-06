package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TablePopulateDao {

    public void populateTables() {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(SQLQuery.populateTableCourse);
            statement.execute(SQLQuery.populateTableGroup);
            statement.execute(SQLQuery.populateTableStudent);
            statement.execute(SQLQuery.populateTableStudentCourse);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}