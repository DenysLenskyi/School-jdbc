package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.FileReader;

import java.sql.*;
import java.util.Random;

public class StudentsCoursesDao {
    FileReader reader = new FileReader();
    private Random random = new Random();
    private final String URL = "jdbc:postgresql://localhost/SchoolJDBC";
    private final String USER = "postgres";
    private final String PASSWORD = "666";
    private final String INIT_TABLE = "/initiate-table-students-courses.sql";
    private final String INSERT_QUERY = "INSERT INTO school.students_courses (STUDENT_ID, COURSE_ID)";
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

    public void createTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute("DROP TABLE IF EXISTS school.students_courses CASCADE");
            statement.execute(reader.readFile(INIT_TABLE));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void populateTable() {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute(createSqlScriptToPopulateStudentsCoursesTable());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createSqlScriptToPopulateStudentsCoursesTable() {
        StringBuilder script = new StringBuilder();
        //this script could enroll one student to one course 3 times
        script.append(INSERT_QUERY).append(NEWLINE).append(VALUES).append(NEWLINE);
        for (int i = 1; i <= 200; i++) {
            int amountOfCourses = random.nextInt(1,4);
            while (amountOfCourses > 0) {
                script.append(OPEN_BRACKET)
                        .append(i)
                        .append(COMA)
                        .append(WHITESPACE)
                        .append(random.nextInt(1,11))
                        .append(CLOSE_BRACKET)
                        .append(COMA)
                        .append(NEWLINE);
                amountOfCourses--;
            }
        }
        return script.deleteCharAt(script.length() - 1)
                .deleteCharAt(script.length() - 1)
                .append(SEMICOLON)
                .toString();
    }

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
