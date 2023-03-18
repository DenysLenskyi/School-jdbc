package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Course;

import java.sql.*;
import java.util.List;

public class CourseDao {

    private static final String SQL_INSERT_INTO_COURSE_TABLE = "INSERT INTO school.course (id, name, description)";
    private static final String NEWLINE = "\n";
    private static final String VALUES = "VALUES";
    private static final String DEFAULT = "default";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";
    private static final String WHITESPACE = " ";
    private static final String COMA = ",";
    private static final String QUOTE = "'";
    private static final String SEMICOLON = ";";

    public void addCourses(List<Course> courses) {
        StringBuilder script = new StringBuilder();
        script.append(SQL_INSERT_INTO_COURSE_TABLE)
                .append(NEWLINE)
                .append(VALUES)
                .append(NEWLINE);
        for (Course course : courses) {
            script.append(OPEN_BRACKET)
                    .append(DEFAULT)
                    .append(COMA)
                    .append(WHITESPACE)
                    .append(QUOTE)
                    .append(course.getName())
                    .append(QUOTE)
                    .append(COMA)
                    .append(WHITESPACE)
                    .append(QUOTE)
                    .append(course.getDescription())
                    .append(QUOTE)
                    .append(CLOSE_BRACKET)
                    .append(COMA)
                    .append(NEWLINE);
        }
        script.deleteCharAt(script.length() - 1)
                .deleteCharAt(script.length() - 1)
                .append(SEMICOLON);
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(script.toString());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Course findCourseById(String sql) {
        Course course = new Course();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                course.setId(rs.getInt(1));
                course.setName(rs.getString(2));
                course.setDescription(rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }
}