package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain;

import java.sql.Connection;
import java.sql.Statement;

public class TablePopulate {

    FileReader reader = new FileReader();
    private static final String SQL_POPULATE_TABLE_COURSES = "/populate-table-courses.sql";

    public void populateTables() {
        populateTableCourses();
        System.out.println("Tables populated");
    }

    public void populateTableCourses() {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(reader.readFile(SQL_POPULATE_TABLE_COURSES));
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            ConnectionManager.close(statement);
            ConnectionManager.close(connection);
        }
    }
}
