package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.FileReader;

import java.sql.*;

public class CoursesDao {
    private final String URL = "jdbc:postgresql://localhost/SchoolJDBC";
    private final String USER = "postgres";
    private final String PASSWORD = "666";
    private final String INIT_TABLE = "/initiate-table-courses.sql";
    private final String COURSES_CONTENT = "/populate-table-courses.sql";
    private final String SQL_FIND_BY_ID = "SELECT ID, NAME, DESCRIPTION FROM school.courses WHERE ID = ";
    FileReader reader = new FileReader();


    public void createTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute("DROP TABLE IF EXISTS school.courses CASCADE");
            statement.execute(reader.readFile(INIT_TABLE));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateCoursesTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute(reader.readFile(COURSES_CONTENT));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findById(String id) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID + id);
             ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                int courseId = rs.getInt(1);
                String name = rs.getString(2);
                String descr = rs.getString(3);
                System.out.println(courseId + "," + name + "," + descr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
