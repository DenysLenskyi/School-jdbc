package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.StudentCourse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao {

    private static final String SQL_INSERT_INTO_STUDENT_COURSE_TABLE =
            "INSERT INTO school.student_course (STUDENT_ID, COURSE_ID)";
    private static final String VALUES = "VALUES";
    private static final String NEWLINE = "\n";
    private static final String COMA = ",";
    private static final String SEMICOLON = ";";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";
    private static final String WHITESPACE = " ";

    public void addStudentCourses(List<StudentCourse> studentsCourses) {
        StringBuilder script = new StringBuilder();
        script.append(SQL_INSERT_INTO_STUDENT_COURSE_TABLE)
                .append(NEWLINE)
                .append(VALUES)
                .append(NEWLINE);
        for (StudentCourse studentCourse : studentsCourses) {
            script.append(OPEN_BRACKET)
                    .append(studentCourse.getStudentId())
                    .append(COMA)
                    .append(WHITESPACE)
                    .append(studentCourse.getCourseId())
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


    public List<Student> getStudentsEnrolledToCourse(String script) {
        List<Student> output = new ArrayList<>();
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(script);
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt(1));
                student.setFirstName(rs.getString(2));
                student.setLastName(rs.getString(3));
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