package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.StringConstant;

import java.sql.*;

public class StudentCourseDao {

    public static final String STUDENT_ID = "Student ID: ";
    public static final String STUDENT_FULL_NAME = "Student name: ";
    private static final String SQL_FIND_STUDENTS_ENROLLED_TO_COURSE = """
            select distinct s.id, first_name, last_name
                from public.student_course s_c
                inner join public.course c on course_id = c.id
                inner join public.student s on s_c.student_id = s.id
                where c.name = 
            """;
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


    public String getStudentsEnrolledToCourse(String courseName) {
        StringBuilder output = new StringBuilder();
        StringBuilder script = new StringBuilder();
        script.append(SQL_FIND_STUDENTS_ENROLLED_TO_COURSE)
                .append(StringConstant.QUOTE)
                .append(courseName)
                .append(StringConstant.QUOTE)
                .append(StringConstant.SEMICOLON);
        output.append("Students enrolled to ")
                .append(courseName)
                .append(" course")
                .append(StringConstant.NEWLINE);
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(script.toString());
             ResultSet rs = preparedStatement.executeQuery()) {
            while (rs.next()) {
                output.append(STUDENT_ID)
                        .append(rs.getInt(1))
                        .append(StringConstant.VERTICAL_BAR)
                        .append(STUDENT_FULL_NAME)
                        .append(rs.getString(2))
                        .append(StringConstant.WHITESPACE)
                        .append(rs.getString(3))
                        .append(StringConstant.NEWLINE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output.toString();
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