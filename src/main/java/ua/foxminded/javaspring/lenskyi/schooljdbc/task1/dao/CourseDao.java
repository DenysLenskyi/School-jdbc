package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Course;

import java.sql.*;
import java.util.List;

public class CourseDao {

    private CourseDao() {
    }

    private static CourseDao courseDao = new CourseDao();

    private static final String ADD_COURSES_QUERY = "INSERT INTO school.course (name, description) VALUES (?,?)";
    private static final String FORMAT = "%1$s%2$s";
    private static final String FIND_COURSE_BY_ID_QUERY =
            "SELECT ID, NAME, DESCRIPTION FROM school.course WHERE ID = ";

    public static CourseDao getCourseDao() {
        return courseDao;
    }

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

    public Course findCourseById(int courseId) {
        Course course = new Course();
        String query = String.format(FORMAT, FIND_COURSE_BY_ID_QUERY, courseId);
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);
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