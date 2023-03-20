package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Course;

import java.sql.*;
import java.util.List;

public class CourseDao {

    private static final String ADD_COURSES_QUERY = "INSERT INTO school.course (name, description) VALUES (?,?)";

    public void addCourses(List<Course> courses) {
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(ADD_COURSES_QUERY)) {
            connection.setAutoCommit(false);
            for (Course course : courses) {
                statement.setString(1, course.getName());
                statement.setString(2, course.getDescription());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
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