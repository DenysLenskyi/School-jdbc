package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TableCreater {

    private final String URL = "jdbc:postgresql://localhost/SchoolJDBC";
    private final String USER = "postgres";
    private final String PASSWORD = "666";
    private final String INITTABLE = "/initiate-table.sql";
    private final String COURSESCONTENT = "/populate-table-courses.sql";
    FileReader reader = new FileReader();


    public void createTable() throws SQLException {
        List<String> tablesToCreate = reader.readFileLines(INITTABLE);
        List<String> courses = reader.readFileLines(COURSESCONTENT);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute("DROP TABLE IF EXISTS school.courses");
            for (String initiateCommand : tablesToCreate) {
                statement.execute(initiateCommand);
            }
            for (String course : courses) {
                statement.execute(course);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
