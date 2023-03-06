package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.ConnectionManager;

import java.sql.Connection;
import java.sql.Statement;

public class TableCreate {

    private static final String SQL_INIT_TABLES = "/initiate-tables.sql";
    FileReader reader = new FileReader();

    public void createNewTables() {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(reader.readFile(SQL_INIT_TABLES));
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Tables created");
    }
}