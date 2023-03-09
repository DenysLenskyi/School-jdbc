package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.sql.*;

public class CourseDao {

    private static final String COURSE_ID = "Course ID: ";
    private static final String COURSE_NAME = "Course name: ";
    private static final String COURSE_DESCRIPTION = "Description: ";
    private static final String VERTICAL_BAR = " | ";
    private static final String NEWLINE = "\n";

    public String findCourseById(String sql) {
        StringBuilder output = new StringBuilder();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                output.append(COURSE_ID)
                        .append(rs.getInt(1))
                        .append(VERTICAL_BAR)
                        .append(COURSE_NAME)
                        .append(rs.getString(2))
                        .append(VERTICAL_BAR)
                        .append(COURSE_DESCRIPTION)
                        .append(rs.getString(3))
                        .append(NEWLINE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output.toString();
    }
}