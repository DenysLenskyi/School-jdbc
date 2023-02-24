package ua.foxminded.javaspring.lenskyi.schooljdbc.task1;

import java.sql.*;
import java.util.List;

public class TableCreater {

    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    Connection connection = null;
    private final String URL = "jdbc:postgresql://localhost/SchoolJDBC";
    private final String USER = "postgres";
    private final String PASSWORD = "666";
    private final String INITTABLE = "/initiate-table.sql";
    private final String COURSESCONTENT = "/populate-table-courses.sql";
    FileReader reader = new FileReader();


    public void createTable() {
        List<String> tablesToCreate = reader.readFileLines(INITTABLE);
        List<String> courses = reader.readFileLines(COURSESCONTENT);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            for (String initiateCommand : tablesToCreate) {
                statement.execute(initiateCommand);
            }
            for (String course : courses) {
                statement.execute(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void executeQuery(String query) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String descr = rs.getString("description");
                System.out.println(id + "," + name + "," + descr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
