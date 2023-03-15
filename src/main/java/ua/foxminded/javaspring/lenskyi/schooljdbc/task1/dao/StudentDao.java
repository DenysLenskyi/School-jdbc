package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import ua.foxminded.javaspring.lenskyi.schooljdbc.task1.command.StringConstant;

import java.sql.*;

public class StudentDao {

    private static final String SQL_ADD_NEW_STUDENT = "insert into public.student values (default, ";
    private static final String SQL_DELETE_STUDENT_BY_ID = "delete from public.student where id = ";

    public void addNewStudent(int groupId, String firstName, String lastName) {
        if (groupId <= 10 && groupId >= 0) {
            StringBuilder script = new StringBuilder();
            script.append(SQL_ADD_NEW_STUDENT);
            if (groupId == 0) {
                script.append(StringConstant.NULL);
            } else {
                script.append(groupId);
            }
            script.append(StringConstant.COMA)
                    .append(StringConstant.WHITESPACE)
                    .append(StringConstant.QUOTE)
                    .append(firstName)
                    .append(StringConstant.QUOTE)
                    .append(StringConstant.COMA)
                    .append(StringConstant.WHITESPACE)
                    .append(StringConstant.QUOTE)
                    .append(lastName)
                    .append(StringConstant.QUOTE)
                    .append(StringConstant.CLOSE_BRACKET)
                    .append(StringConstant.SEMICOLON);
            try (Connection connection = ConnectionManager.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.execute(script.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Student added");
        } else {
            System.out.println("Student hasn't been added!\nGroup ID should be a number in range from 0 to 10");
        }
    }

    public void deleteStudentById(int studentId) {
        StringBuilder script = new StringBuilder();
        script.append(SQL_DELETE_STUDENT_BY_ID)
                .append(studentId)
                .append(StringConstant.SEMICOLON);
        try (Connection connection = ConnectionManager.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(script.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Student deleted");
    }
}