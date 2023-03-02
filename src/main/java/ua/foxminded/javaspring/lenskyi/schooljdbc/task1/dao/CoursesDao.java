package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain.DatabaseProperties;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.domain.StringConstant;

import java.sql.*;

public class CoursesDao {

    String url = DatabaseProperties.getDbUrl();
    String user = DatabaseProperties.getDbUserName();
    String password = DatabaseProperties.getDbPassword();
    private static final String SQL_FIND_BY_ID = "SELECT ID, NAME, DESCRIPTION FROM course WHERE ID = ";
    private static final String COURSE_ID = "Course ID: ";
    private static final String COURSE_NAME = "Course name: ";
    private static final String COURSE_DESCRIPTION = "Description: ";

    public String findCourseById(String id) {
        StringBuilder output = new StringBuilder();
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID + id);
             ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                output.append(COURSE_ID)
                        .append(rs.getInt(1))
                        .append(StringConstant.VERTICAL_BAR)
                        .append(COURSE_NAME)
                        .append(rs.getString(2))
                        .append(StringConstant.VERTICAL_BAR)
                        .append(COURSE_DESCRIPTION)
                        .append(rs.getString(3))
                        .append(StringConstant.NEWLINE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}