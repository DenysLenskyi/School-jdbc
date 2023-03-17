package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao {


    public List<Student> getStudentsEnrolledToCourse(String script) {
        List<Student> output = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(script);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.studentId = rs.getInt(1);
                student.studentFirstName = rs.getString(2);
                student.studentLastName = rs.getString(3);
                output.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output;
    }

    public void addStudentToCourse(String script) {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(script);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentFromCourse(String script) {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(script);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}