package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain;

import java.sql.Connection;
import java.sql.Statement;

public class TableCreate {

    private static final String SQL_INIT_TABLES = "/initiate-tables.sql";

    FileReader reader = new FileReader();

    public void createNewTables() {
        ConnectionManager connectionManager = new ConnectionManager();
        Connection connection = connectionManager.getConnection();
        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute(reader.readFile(SQL_INIT_TABLES));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.close(connection);
            ConnectionManager.close(statement);
        }
        System.out.println("Tables created");
    }
}
