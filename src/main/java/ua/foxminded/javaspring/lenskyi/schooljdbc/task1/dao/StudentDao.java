package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao.domain.Student;

import java.sql.*;
import java.util.List;

public class StudentDao {

    private static final String SQL_INSERT_INTO_STUDENT_TABLE =
            "INSERT INTO school.student (id, group_id, first_name, last_name)";
    private static final String NEWLINE = "\n";
    private static final String VALUES = "VALUES";
    private static final String DEFAULT = "default";
    private static final String OPEN_BRACKET = "(";
    private static final String CLOSE_BRACKET = ")";
    private static final String WHITESPACE = " ";
    private static final String COMA = ",";
    private static final String QUOTE = "'";
    private static final String SEMICOLON = ";";
    private static final String NULL = "NULL";

    public void addStudents(List<Student> students) {
        StringBuilder script = new StringBuilder();
        script.append(SQL_INSERT_INTO_STUDENT_TABLE)
                .append(NEWLINE)
                .append(VALUES)
                .append(NEWLINE);
        for (Student student : students) {
            script.append(OPEN_BRACKET)
                    .append(DEFAULT)
                    .append(COMA)
                    .append(WHITESPACE);
            if (student.getGroupId() == 0) {
                script.append(NULL);
            } else {
                script.append(student.getGroupId());
            }
            script.append(COMA)
                    .append(WHITESPACE)
                    .append(QUOTE)
                    .append(student.getFirstName())
                    .append(QUOTE)
                    .append(COMA)
                    .append(WHITESPACE)
                    .append(QUOTE)
                    .append(student.getLastName())
                    .append(QUOTE)
                    .append(CLOSE_BRACKET)
                    .append(COMA)
                    .append(WHITESPACE);
        }
        script.deleteCharAt(script.length() - 1)
                .deleteCharAt(script.length() - 1)
                .append(SEMICOLON);
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(script.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewStudent(String script) {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(script);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentById(String script) {
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(script);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}