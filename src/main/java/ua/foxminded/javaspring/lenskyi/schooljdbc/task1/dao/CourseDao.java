package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Course;

import java.sql.*;

public class CourseDao {

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