package ua.foxminded.javaspring.lenskyi.schooljdbc.task1.dao;

import java.sql.*;

public class StudentsDao {
    private final String URL = "jdbc:postgresql://localhost/SchoolJDBC";
    private final String USER = "postgres";
    private final String PASSWORD = "666";
    private final String NULL = "NULL";
    private final String NEWLINE = "\n";
    private static final String GROUP_ID = "Group ID: ";
    private static final String VERTICAL_BAR = " | ";
    private static final String COMA = ",";
    private static final String CLOSE_BRACKET = ")";
    private static final String GROUP_NAME = "Group name: ";
    private static final String NUM_STUDENTS = "Students in group: ";
    private static final String WHITESPACE = " ";
    private static final String SEMICOLON = ";";
    private static final String QUOTE = "'";
    private static final String SQL_GROUPS_WITH_EQUAL_OR_LESS_NUM_STUDENTS = """
            select group_id, name, count(student_id)
             from school.students
             inner join school.groups on group_id = id
             group by name, group_id
             having count(student_id) <= """;
    private static final String SQL_DELETE_STUDENT_BY_ID = "delete from school.students where student_id = ";
    private static final String SQL_ADD_NEW_STUDENT = "insert into school.students values (default, ";

    public String getGroupWithAmountOfStudents(int numStudents) {
        StringBuilder output = new StringBuilder();
        StringBuilder script = new StringBuilder();
        script.append(SQL_GROUPS_WITH_EQUAL_OR_LESS_NUM_STUDENTS)
                .append(numStudents)
                .append(" order by group_id;");
        output.append("Groups with less or equal than ")
                .append(numStudents)
                .append(" students")
                .append(NEWLINE);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(script.toString());
             ResultSet rs = preparedStatement.executeQuery();) {
            while (rs.next()) {
                output.append(GROUP_ID)
                        .append(rs.getInt(1))
                        .append(VERTICAL_BAR)
                        .append(WHITESPACE)
                        .append(GROUP_NAME)
                        .append(rs.getString(2))
                        .append(VERTICAL_BAR)
                        .append(WHITESPACE)
                        .append(NUM_STUDENTS)
                        .append(rs.getInt(3))
                        .append(VERTICAL_BAR)
                        .append(NEWLINE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

    public void addNewStudent(int groupId, String firstName, String lastName) {
        if (groupId <= 10 && groupId >= 0) {
            StringBuilder script = new StringBuilder();
            script.append(SQL_ADD_NEW_STUDENT);
            if (groupId == 0) {
                script.append(NULL);
            } else {
                script.append(groupId);
            }
            script.append(COMA)
                    .append(WHITESPACE)
                    .append(QUOTE)
                    .append(firstName)
                    .append(QUOTE)
                    .append(COMA)
                    .append(WHITESPACE)
                    .append(QUOTE)
                    .append(lastName)
                    .append(QUOTE)
                    .append(CLOSE_BRACKET)
                    .append(SEMICOLON);
            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement statement = connection.createStatement();) {
                statement.execute(script.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Student added");
        } else {
            System.out.println("Group ID should be a number in range from 0 to 10");
        }
    }

    public void deleteStudentById(int studentId) {
        StringBuilder script = new StringBuilder();
        script.append(SQL_DELETE_STUDENT_BY_ID)
                .append(studentId)
                .append(SEMICOLON);
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();) {
            statement.execute(script.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Student deleted");
    }
}