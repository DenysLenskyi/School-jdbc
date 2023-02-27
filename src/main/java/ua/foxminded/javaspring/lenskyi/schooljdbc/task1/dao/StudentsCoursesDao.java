package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.FileReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
    public final String OPEN_BRACKET = "(";
    public final String CLOSE_BRACKET = ")";
    public final String COMA = ",";
    public final String WHITESPACE = " ";

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
}
