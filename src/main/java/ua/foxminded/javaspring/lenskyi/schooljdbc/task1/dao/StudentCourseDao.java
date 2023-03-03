package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.sql.*;

public class StudentCourseDao {

    private final String URL = "jdbc:postgresql://localhost/SchoolJDBC";
    private final String USER = "postgres";
    private final String PASSWORD = "666";

    public static final String NEWLINE = "\n";
    public static final String SEMICOLON = ";";
    public static final String VALUES = "VALUES";
    public static final String VERTICAL_BAR = " | ";
    public static final String STUDENT_ID = "Student ID: ";
    public static final String STUDENT_FULL_NAME = "Student name: ";
    public static final String QUOTE = "'";
    public final String OPEN_BRACKET = "(";
    public final String CLOSE_BRACKET = ")";
    public final String COMA = ",";
    public final String WHITESPACE = " ";
    private static final String SQL_FIND_STUDENTS_ENROLLED_TO_COURSE = """
            select distinct ss.student_id, first_name, last_name
            from school.students_courses ssc
            inner join school.courses sc on course_id = sc.id
            inner join school.students ss on ssc.student_id = ss.student_id
            where sc.name = 
            """;
    private static final String SQL_ADD_STUDENT_TO_COURSE_FIRST_PART = """
            insert into school.students_courses (student_id, course_id)
            select 
            """;
    private static final String SQL_ADD_STUDENT_TO_COURSE_SECOND_PART = """
            , sc.id
            from school.courses sc
            where sc.name = 
            """;
    private static final String SQL_REMOVE_STUDENT_FROM_COURSE_FIRST_PART = """
            delete from school.students_courses
            where student_id = 
            """;
    private static final String SQL_REMOVE_STUDENT_FROM_COURSE_SECOND_PART = """
             and course_id in (select
            id from school.courses where name = 
            """;



    public String getStudentsEnrolledToCourse(String courseName) {
        StringBuilder output = new StringBuilder();
        StringBuilder script = new StringBuilder();
        script.append(SQL_FIND_STUDENTS_ENROLLED_TO_COURSE)
                .append(QUOTE)
                .append(courseName)
                .append(QUOTE)
                .append(SEMICOLON);
        output.append("Students enrolled to ")
                .append(courseName)
                .append(" course")
                .append(NEWLINE);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script.toString());
             ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                output.append(STUDENT_ID)
                        .append(rs.getInt(1))
                        .append(VERTICAL_BAR)
                        .append(STUDENT_FULL_NAME)
                        .append(rs.getString(2))
                        .append(WHITESPACE)
                        .append(rs.getString(3))
                        .append(NEWLINE);
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
                .append(QUOTE)
                .append(courseName)
                .append(QUOTE)
                .append(SEMICOLON);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
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
                .append(QUOTE)
                .append(courseName)
                .append(QUOTE)
                .append(CLOSE_BRACKET)
                .append(SEMICOLON);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute(script.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Student remover from course");
    }
}
