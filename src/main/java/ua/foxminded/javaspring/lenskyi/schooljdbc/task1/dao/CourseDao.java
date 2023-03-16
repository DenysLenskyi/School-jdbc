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
                course.courseId = rs.getInt(1);
                course.courseName = rs.getString(2);
                course.courseDescription = rs.getString(3);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return course;
    }
}