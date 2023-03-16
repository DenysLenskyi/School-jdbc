package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.StringConstant;
import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentCourseDao {

    private static final String SQL_ADD_STUDENT_TO_COURSE_FIRST_PART = """
            insert into public.student_course (student_id, course_id)
            select 
            """;
    private static final String SQL_ADD_STUDENT_TO_COURSE_SECOND_PART = """
            , c.id
            from public.course c
            where c.name = 
            """;
    private static final String SQL_REMOVE_STUDENT_FROM_COURSE_FIRST_PART = """
            delete from public.student_course
            where student_id = 
            """;
    private static final String SQL_REMOVE_STUDENT_FROM_COURSE_SECOND_PART = """
             and course_id in (select
            id from public.course where name = 
            """;


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

    public void addStudentToCourse(int studentId, String courseName) {
        StringBuilder script = new StringBuilder();
        script.append(SQL_ADD_STUDENT_TO_COURSE_FIRST_PART)
                .append(studentId)
                .append(SQL_ADD_STUDENT_TO_COURSE_SECOND_PART)
                .append(StringConstant.QUOTE)
                .append(courseName)
                .append(StringConstant.QUOTE)
                .append(StringConstant.SEMICOLON);
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute(script.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Student enrolled to course");
    }

    public void removeStudentFromCourse(int studentId, String courseName) {
        StringBuilder script = new StringBuilder();
        script.append(SQL_REMOVE_STUDENT_FROM_COURSE_FIRST_PART)
                .append(studentId)
                .append(SQL_REMOVE_STUDENT_FROM_COURSE_SECOND_PART)
                .append(StringConstant.QUOTE)
                .append(courseName)
                .append(StringConstant.QUOTE)
                .append(StringConstant.CLOSE_BRACKET)
                .append(StringConstant.SEMICOLON);
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {
            statement.execute(script.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Student removed from course");
    }
}